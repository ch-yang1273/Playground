import { PollPageContent } from "@/components/poll-page-content"

interface PollPageProps {
  params: Promise<{ id: string }>
}

export default async function PollPage({ params }: PollPageProps) {
  const { id } = await params
  return <PollPageContent pollId={id} />
}
