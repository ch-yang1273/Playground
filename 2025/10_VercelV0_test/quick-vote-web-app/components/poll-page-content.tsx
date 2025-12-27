"use client"

import { useState, useEffect, useCallback } from "react"
import useSWR from "swr"
import { VotingInterface } from "@/components/voting-interface"
import { ResultsDashboard } from "@/components/results-dashboard"
import { Button } from "@/components/ui/button"
import { Share2, Check, ArrowLeft, Vote } from "lucide-react"
import Link from "next/link"
import type { Poll } from "@/lib/poll-store"

const fetcher = (url: string) => fetch(url).then((res) => res.json())

interface PollPageContentProps {
  pollId: string
}

export function PollPageContent({ pollId }: PollPageContentProps) {
  const [hasVoted, setHasVoted] = useState(false)
  const [selectedOption, setSelectedOption] = useState<string | null>(null)
  const [copied, setCopied] = useState(false)

  const { data: poll, error, mutate } = useSWR<Poll>(`/api/polls/${pollId}`, fetcher, { refreshInterval: 2000 })

  // Check if user has already voted (stored in localStorage)
  useEffect(() => {
    const votedPolls = JSON.parse(localStorage.getItem("votedPolls") || "{}")
    if (votedPolls[pollId]) {
      setHasVoted(true)
      setSelectedOption(votedPolls[pollId])
    }
  }, [pollId])

  const handleVote = useCallback(
    async (optionId: string) => {
      setSelectedOption(optionId)
      setHasVoted(true)

      // Store vote in localStorage
      const votedPolls = JSON.parse(localStorage.getItem("votedPolls") || "{}")
      votedPolls[pollId] = optionId
      localStorage.setItem("votedPolls", JSON.stringify(votedPolls))

      // Send vote to server
      await fetch(`/api/polls/${pollId}/vote`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ optionId }),
      })

      // Refresh poll data
      mutate()
    },
    [pollId, mutate],
  )

  const handleShare = async () => {
    const url = window.location.href
    try {
      if (navigator.share) {
        await navigator.share({
          title: poll?.subject || "QuickVote Poll",
          url,
        })
        return
      }
    } catch {
      // Share was cancelled or failed, fall back to clipboard
    }

    // Fallback: copy to clipboard
    try {
      await navigator.clipboard.writeText(url)
      setCopied(true)
      setTimeout(() => setCopied(false), 2000)
    } catch {
      // Clipboard also failed, show manual copy prompt
      window.prompt("Copy this link:", url)
    }
  }

  if (error) {
    return (
      <div className="min-h-screen bg-background flex items-center justify-center px-4">
        <div className="text-center">
          <h1 className="text-2xl font-bold text-foreground mb-2">Poll Not Found</h1>
          <p className="text-muted-foreground mb-4">This poll may have expired or doesn&apos;t exist.</p>
          <Link href="/">
            <Button>Create a New Poll</Button>
          </Link>
        </div>
      </div>
    )
  }

  if (!poll) {
    return (
      <div className="min-h-screen bg-background flex items-center justify-center">
        <div className="flex items-center gap-3">
          <div className="w-8 h-8 border-4 border-primary border-t-transparent rounded-full animate-spin" />
          <span className="text-muted-foreground">Loading poll...</span>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-background px-4 py-6">
      <div className="max-w-lg mx-auto">
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <Link
            href="/"
            className="inline-flex items-center gap-2 text-sm text-muted-foreground hover:text-foreground transition-colors"
          >
            <ArrowLeft className="w-4 h-4" />
            Home
          </Link>
          <div className="flex items-center gap-2">
            <div className="flex items-center gap-1.5 text-sm text-muted-foreground">
              <Vote className="w-4 h-4" />
              <span className="font-mono">{pollId}</span>
            </div>
          </div>
        </div>

        {/* Voting Interface */}
        <div className="mb-6">
          <VotingInterface poll={poll} onVote={handleVote} hasVoted={hasVoted} selectedOption={selectedOption} />
        </div>

        {/* Results Dashboard - Always visible */}
        <div className="mb-6">
          <ResultsDashboard poll={poll} />
        </div>

        {/* Share Button */}
        <Button variant="outline" onClick={handleShare} className="w-full h-12 text-base bg-transparent">
          {copied ? (
            <>
              <Check className="w-4 h-4 mr-2" />
              Link Copied!
            </>
          ) : (
            <>
              <Share2 className="w-4 h-4 mr-2" />
              Share Poll
            </>
          )}
        </Button>

        {/* Real-time indicator */}
        <div className="mt-4 flex items-center justify-center gap-2 text-xs text-muted-foreground">
          <span className="w-2 h-2 rounded-full bg-green-500 animate-pulse" />
          Results update in real-time
        </div>
      </div>
    </div>
  )
}
