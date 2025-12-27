"use client"

import type React from "react"

import { Button } from "@/components/ui/button"
import { Vote, Zap, Users, BarChart3 } from "lucide-react"
import Link from "next/link"

export function HeroSection() {
  return (
    <section className="min-h-screen flex flex-col">
      {/* Header */}
      <header className="flex items-center justify-between px-4 py-4 md:px-8">
        <div className="flex items-center gap-2">
          <div className="flex items-center justify-center w-10 h-10 rounded-lg bg-primary">
            <Vote className="w-5 h-5 text-primary-foreground" />
          </div>
          <span className="text-xl font-bold text-foreground">QuickVote</span>
        </div>
      </header>

      {/* Hero Content */}
      <div className="flex-1 flex flex-col items-center justify-center px-4 py-12 text-center">
        <div className="inline-flex items-center gap-2 px-4 py-2 mb-6 text-sm font-medium rounded-full bg-secondary text-secondary-foreground">
          <Zap className="w-4 h-4" />
          <span>Instant polls, real-time results</span>
        </div>

        <h1 className="text-4xl md:text-6xl font-bold text-foreground mb-4 text-balance max-w-3xl leading-tight">
          Create polls in seconds, <span className="text-primary">get answers instantly</span>
        </h1>

        <p className="text-lg md:text-xl text-muted-foreground mb-8 max-w-2xl text-pretty">
          QuickVote makes it easy to gather opinions from your team, friends, or audience. No sign-up required.
        </p>

        <Link href="/create">
          <Button size="lg" className="text-lg px-8 py-6 font-semibold">
            Start a New Poll
          </Button>
        </Link>

        {/* Feature Cards */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mt-16 w-full max-w-4xl">
          <FeatureCard
            icon={<Zap className="w-6 h-6" />}
            title="Lightning Fast"
            description="Create a poll in under 30 seconds"
          />
          <FeatureCard
            icon={<Users className="w-6 h-6" />}
            title="No Sign-up"
            description="Anyone can vote with just a link"
          />
          <FeatureCard
            icon={<BarChart3 className="w-6 h-6" />}
            title="Live Results"
            description="Watch votes come in real-time"
          />
        </div>
      </div>
    </section>
  )
}

function FeatureCard({
  icon,
  title,
  description,
}: {
  icon: React.ReactNode
  title: string
  description: string
}) {
  return (
    <div className="flex flex-col items-center p-6 rounded-xl bg-secondary/50 border border-border">
      <div className="flex items-center justify-center w-12 h-12 mb-4 rounded-lg bg-primary/10 text-primary">
        {icon}
      </div>
      <h3 className="text-lg font-semibold text-foreground mb-2">{title}</h3>
      <p className="text-sm text-muted-foreground text-center">{description}</p>
    </div>
  )
}
