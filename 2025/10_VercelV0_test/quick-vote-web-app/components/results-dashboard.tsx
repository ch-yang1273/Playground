"use client"

import { useEffect, useState } from "react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Users, BarChart3 } from "lucide-react"
import type { Poll } from "@/lib/poll-store"

interface ResultsDashboardProps {
  poll: Poll
}

export function ResultsDashboard({ poll }: ResultsDashboardProps) {
  const [animatedPercentages, setAnimatedPercentages] = useState<number[]>(poll.options.map(() => 0))

  // Animate percentages when poll data changes
  useEffect(() => {
    const targetPercentages = poll.options.map((option) =>
      poll.totalVotes > 0 ? (option.votes / poll.totalVotes) * 100 : 0,
    )

    const animationDuration = 600
    const steps = 30
    const stepDuration = animationDuration / steps

    let currentStep = 0
    const interval = setInterval(() => {
      currentStep++
      const progress = currentStep / steps
      const easeOut = 1 - Math.pow(1 - progress, 3)

      setAnimatedPercentages(targetPercentages.map((target) => target * easeOut))

      if (currentStep >= steps) {
        clearInterval(interval)
        setAnimatedPercentages(targetPercentages)
      }
    }, stepDuration)

    return () => clearInterval(interval)
  }, [poll])

  const maxVotes = Math.max(...poll.options.map((o) => o.votes), 1)

  // Generate colors for bars
  const barColors = ["bg-primary", "bg-blue-400", "bg-sky-400", "bg-cyan-500", "bg-teal-500"]

  return (
    <Card className="border-border shadow-lg">
      <CardHeader>
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <BarChart3 className="w-5 h-5 text-primary" />
            <CardTitle className="text-lg font-semibold">Live Results</CardTitle>
          </div>
          <div className="flex items-center gap-2 px-3 py-1.5 rounded-full bg-secondary">
            <Users className="w-4 h-4 text-primary" />
            <span className="text-sm font-semibold text-foreground">{poll.totalVotes}</span>
            <span className="text-sm text-muted-foreground">{poll.totalVotes === 1 ? "vote" : "votes"}</span>
          </div>
        </div>
      </CardHeader>
      <CardContent className="flex flex-col gap-4">
        {poll.options.map((option, index) => {
          const percentage = animatedPercentages[index] || 0
          const isLeading = option.votes === maxVotes && option.votes > 0

          return (
            <div key={option.id} className="flex flex-col gap-2">
              <div className="flex items-center justify-between text-sm">
                <span className={`font-medium ${isLeading ? "text-primary" : "text-foreground"}`}>
                  {option.text}
                  {isLeading && poll.totalVotes > 0 && (
                    <span className="ml-2 text-xs text-primary font-semibold">Leading</span>
                  )}
                </span>
                <span className="text-muted-foreground">
                  {option.votes} ({percentage.toFixed(1)}%)
                </span>
              </div>
              <div className="h-8 bg-secondary rounded-lg overflow-hidden">
                <div
                  className={`h-full ${barColors[index % barColors.length]} transition-all duration-500 ease-out rounded-lg flex items-center justify-end px-3`}
                  style={{ width: `${Math.max(percentage, 0)}%` }}
                >
                  {percentage > 15 && (
                    <span className="text-xs font-semibold text-white">{percentage.toFixed(0)}%</span>
                  )}
                </div>
              </div>
            </div>
          )
        })}
      </CardContent>
    </Card>
  )
}
