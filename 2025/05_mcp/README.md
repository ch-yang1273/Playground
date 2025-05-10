# Case Converter MCP Server

Node.js server implementing Model Context Protocol (MCP) for text case conversion.

## Features

- Convert text strings to uppercase or lowercase

## API

### Tools

- **convert_case**
    - Convert text to uppercase or lowercase
    - Inputs:
        - `text` (string): The text to convert
        - `targetCase` (string): Either 'upper' or 'lower'
    - Returns the converted text string

## Usage with Claude Desktop
Add this to your `claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "case-converter": {
      "command": "npx",
      "args": [
        "-y",
        "@ch-yang/mcp-case-converter"
      ]
    }
  }
}
```