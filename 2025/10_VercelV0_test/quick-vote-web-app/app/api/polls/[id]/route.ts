import { type NextRequest, NextResponse } from "next/server"
import { getPoll } from "@/lib/poll-store"

export async function GET(request: NextRequest, { params }: { params: Promise<{ id: string }> }) {
  const { id } = await params
  const poll = getPoll(id)

  if (!poll) {
    return NextResponse.json({ error: "Poll not found" }, { status: 404 })
  }

  return NextResponse.json(poll)
}
