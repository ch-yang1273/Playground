export interface PollOption {
  id: string
  text: string
  votes: number
}

export interface Poll {
  id: string
  subject: string
  options: PollOption[]
  createdAt: Date
  totalVotes: number
}

// In-memory store for demo purposes
const polls = new Map<string, Poll>()

export function generateId(): string {
  return Math.random().toString(36).substring(2, 8).toUpperCase()
}

export function createPoll(subject: string, optionTexts: string[]): Poll {
  const id = generateId()
  const poll: Poll = {
    id,
    subject,
    options: optionTexts.map((text, index) => ({
      id: `opt-${index}`,
      text,
      votes: 0,
    })),
    createdAt: new Date(),
    totalVotes: 0,
  }
  polls.set(id, poll)
  return poll
}

export function getPoll(id: string): Poll | undefined {
  return polls.get(id)
}

export function votePoll(pollId: string, optionId: string): Poll | undefined {
  const poll = polls.get(pollId)
  if (!poll) return undefined

  const option = poll.options.find((o) => o.id === optionId)
  if (option) {
    option.votes += 1
    poll.totalVotes += 1
  }
  return poll
}

export function getAllPolls(): Poll[] {
  return Array.from(polls.values())
}
