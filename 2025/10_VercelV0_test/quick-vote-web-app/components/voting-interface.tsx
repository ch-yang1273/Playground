"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"
import { Check, Vote } from "lucide-react"
import type { Poll } from "@/lib/poll-store"

interface VotingInterfaceProps {
  poll: Poll
  onVote: (optionId: string) => void
  hasVoted: boolean
  selectedOption: string | null
}

export function VotingInterface({ poll, onVote, hasVoted, selectedOption }: VotingInterfaceProps) {
  const [hoveredOption, setHoveredOption] = useState<string | null>(null)

  return (
    <Card className="border-border shadow-lg">
      <CardHeader className="text-center pb-4">
        <div className="flex items-center justify-center w-14 h-14 mx-auto mb-4 rounded-full bg-primary/10">
          <Vote className="w-7 h-7 text-primary" />
        </div>
        <CardTitle className="text-xl md:text-2xl font-bold text-foreground text-balance">{poll.subject}</CardTitle>
        <CardDescription>
          {hasVoted ? "Thanks for voting! View results below." : "Tap an option to cast your vote"}
        </CardDescription>
      </CardHeader>
      <CardContent className="flex flex-col gap-3">
        {poll.options.map((option) => {
          const isSelected = selectedOption === option.id
          const isHovered = hoveredOption === option.id

          return (
            <Button
              key={option.id}
              variant={isSelected ? "default" : "outline"}
              className={`
                w-full h-16 md:h-20 text-base md:text-lg font-medium
                transition-all duration-200 ease-out
                ${isSelected ? "ring-2 ring-primary ring-offset-2" : ""}
                ${isHovered && !hasVoted ? "scale-[1.02] shadow-md" : ""}
                ${hasVoted && !isSelected ? "opacity-60" : ""}
              `}
              onClick={() => !hasVoted && onVote(option.id)}
              onMouseEnter={() => setHoveredOption(option.id)}
              onMouseLeave={() => setHoveredOption(null)}
              disabled={hasVoted}
            >
              <span className="flex items-center gap-3">
                {isSelected && <Check className="w-5 h-5" />}
                {option.text}
              </span>
            </Button>
          )
        })}
      </CardContent>
    </Card>
  )
}
