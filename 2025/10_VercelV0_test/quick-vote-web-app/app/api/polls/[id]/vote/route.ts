import { type NextRequest, NextResponse } from "next/server"
import { votePoll } from "@/lib/poll-store"

export async function POST(request: NextRequest, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params
    const body = await request.json()
    const { optionId } = body

    if (!optionId) {
      return NextResponse.json({ error: "Option ID required" }, { status: 400 })
    }

    const poll = votePoll(id, optionId)

    if (!poll) {
      return NextResponse.json({ error: "Poll not found" }, { status: 404 })
    }

    return NextResponse.json(poll)
  } catch {
    return NextResponse.json({ error: "Failed to vote" }, { status: 500 })
  }
}
