"use client"

import type React from "react"

import { useState } from "react"
import { useRouter } from "next/navigation"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"
import { Plus, Trash2, ArrowLeft } from "lucide-react"
import Link from "next/link"

export function PollCreationForm() {
  const router = useRouter()
  const [subject, setSubject] = useState("")
  const [options, setOptions] = useState(["", ""])
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState("")

  const addOption = () => {
    if (options.length < 5) {
      setOptions([...options, ""])
    }
  }

  const removeOption = (index: number) => {
    if (options.length > 2) {
      setOptions(options.filter((_, i) => i !== index))
    }
  }

  const updateOption = (index: number, value: string) => {
    const newOptions = [...options]
    newOptions[index] = value
    setOptions(newOptions)
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setError("")

    // Validation
    if (!subject.trim()) {
      setError("Please enter a poll subject")
      return
    }

    const validOptions = options.filter((opt) => opt.trim())
    if (validOptions.length < 2) {
      setError("Please enter at least 2 options")
      return
    }

    setIsLoading(true)

    try {
      const response = await fetch("/api/polls", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ subject: subject.trim(), options: validOptions }),
      })

      const data = await response.json()

      if (data.id) {
        router.push(`/poll/${data.id}`)
      }
    } catch {
      setError("Failed to create poll. Please try again.")
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <div className="min-h-screen bg-background px-4 py-8">
      <div className="max-w-lg mx-auto">
        <Link
          href="/"
          className="inline-flex items-center gap-2 text-sm text-muted-foreground hover:text-foreground mb-6 transition-colors"
        >
          <ArrowLeft className="w-4 h-4" />
          Back to home
        </Link>

        <Card className="border-border shadow-lg">
          <CardHeader>
            <CardTitle className="text-2xl font-bold text-foreground">Create a Poll</CardTitle>
            <CardDescription>Add a question and 2-5 options for people to vote on</CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit} className="flex flex-col gap-6">
              {/* Subject Input */}
              <div className="flex flex-col gap-2">
                <Label htmlFor="subject" className="text-sm font-medium">
                  Poll Question
                </Label>
                <Input
                  id="subject"
                  placeholder="What should we have for lunch?"
                  value={subject}
                  onChange={(e) => setSubject(e.target.value)}
                  className="h-12"
                />
              </div>

              {/* Options */}
              <div className="flex flex-col gap-3">
                <Label className="text-sm font-medium">Options</Label>
                {options.map((option, index) => (
                  <div key={index} className="flex items-center gap-2">
                    <Input
                      placeholder={`Option ${index + 1}`}
                      value={option}
                      onChange={(e) => updateOption(index, e.target.value)}
                      className="h-12 flex-1"
                    />
                    {options.length > 2 && (
                      <Button
                        type="button"
                        variant="ghost"
                        size="icon"
                        onClick={() => removeOption(index)}
                        className="text-muted-foreground hover:text-destructive shrink-0"
                      >
                        <Trash2 className="w-4 h-4" />
                      </Button>
                    )}
                  </div>
                ))}

                {options.length < 5 && (
                  <Button
                    type="button"
                    variant="outline"
                    onClick={addOption}
                    className="w-full h-12 border-dashed bg-transparent"
                  >
                    <Plus className="w-4 h-4 mr-2" />
                    Add Option
                  </Button>
                )}
              </div>

              {error && <p className="text-sm text-destructive font-medium">{error}</p>}

              <Button type="submit" size="lg" className="w-full h-12 text-base font-semibold" disabled={isLoading}>
                {isLoading ? "Creating..." : "Create Poll"}
              </Button>
            </form>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
