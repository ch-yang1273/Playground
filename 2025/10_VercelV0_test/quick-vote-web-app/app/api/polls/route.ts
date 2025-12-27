import { type NextRequest, NextResponse } from "next/server"
import { createPoll, getAllPolls } from "@/lib/poll-store"

export async function POST(request: NextRequest) {
  try {
    const body = await request.json()
    const { subject, options } = body

    if (!subject || !options || options.length < 2) {
      return NextResponse.json({ error: "Invalid poll data" }, { status: 400 })
    }

    const poll = createPoll(subject, options)
    return NextResponse.json(poll)
  } catch {
    return NextResponse.json({ error: "Failed to create poll" }, { status: 500 })
  }
}

export async function GET() {
  const polls = getAllPolls()
  return NextResponse.json(polls)
}
