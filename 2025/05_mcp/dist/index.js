#!/usr/bin/env node
import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import { CallToolRequestSchema, ListToolsRequestSchema, ToolSchema, } from "@modelcontextprotocol/sdk/types.js";
import { z } from "zod";
import { zodToJsonSchema } from "zod-to-json-schema";
// Schema definitions
const ConvertCaseArgsSchema = z.object({
    text: z.string().describe('The text to convert'),
    targetCase: z.enum(['upper', 'lower']).describe('The target case (upper or lower)'),
});
const ToolInputSchema = ToolSchema.shape.inputSchema;
// Server setup
const server = new Server({
    name: "case-converter-server",
    version: "0.1.0",
}, {
    capabilities: {
        tools: {},
    },
});
// Case conversion functions
function convertCase(text, targetCase) {
    if (targetCase === 'upper') {
        return text.toUpperCase();
    }
    else {
        return text.toLowerCase();
    }
}
// Tool handlers
server.setRequestHandler(ListToolsRequestSchema, async () => {
    return {
        tools: [
            {
                name: "convert_case",
                description: "Convert the case of a string to uppercase or lowercase. " +
                    "Takes a text string and the target case ('upper' or 'lower') as input. " +
                    "Returns the converted string.",
                inputSchema: zodToJsonSchema(ConvertCaseArgsSchema),
            },
        ],
    };
});
server.setRequestHandler(CallToolRequestSchema, async (request) => {
    try {
        const { name, arguments: args } = request.params;
        switch (name) {
            case "convert_case": {
                const parsed = ConvertCaseArgsSchema.safeParse(args);
                if (!parsed.success) {
                    throw new Error(`Invalid arguments for convert_case: ${parsed.error}`);
                }
                const result = convertCase(parsed.data.text, parsed.data.targetCase);
                return {
                    content: [{ type: "text", text: result }],
                };
            }
            default:
                throw new Error(`Unknown tool: ${name}`);
        }
    }
    catch (error) {
        const errorMessage = error instanceof Error ? error.message : String(error);
        return {
            content: [{ type: "text", text: `Error: ${errorMessage}` }],
            isError: true,
        };
    }
});
// Start server
async function runServer() {
    const transport = new StdioServerTransport();
    await server.connect(transport);
    console.error("Case Converter MCP Server running on stdio");
}
runServer().catch((error) => {
    console.error("Fatal error running server:", error);
    process.exit(1);
});
