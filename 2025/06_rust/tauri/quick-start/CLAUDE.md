# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Tauri v2 application with a vanilla JavaScript frontend and Rust backend. The project demonstrates basic Tauri functionality with a simple greeting interface that calls Rust commands from JavaScript.

## Development Commands

### Frontend Development
- `npm run tauri dev` - Start development server with hot reload for both frontend and backend
- `npm run tauri build` - Build production bundles and installers

### Backend Development (Rust)
- `cd src-tauri && cargo check` - Check Rust code for errors without building
- `cd src-tauri && cargo build` - Build Rust backend
- `cd src-tauri && cargo test` - Run Rust tests
- `cd src-tauri && cargo clippy` - Run Rust linter

### Tauri CLI Commands
- `npx tauri info` - Show environment and project information
- `npx tauri add <plugin>` - Add Tauri plugins
- `npx tauri icon` - Generate app icons from source image
- `npx tauri permission` - Manage app permissions
- `npx tauri capability` - Manage app capabilities

## Architecture

### File Structure
- `src/` - Frontend code (HTML, CSS, JavaScript)
  - `main.js` - Frontend entry point with Tauri API usage
  - `index.html` - Main HTML template
  - `styles.css` - Application styles
- `src-tauri/` - Rust backend
  - `src/main.rs` - Binary entry point
  - `src/lib.rs` - Library with Tauri commands and app setup
  - `tauri.conf.json` - Tauri configuration
  - `capabilities/` - Permission capabilities definitions
  - `icons/` - Application icons for different platforms

### Key Architecture Patterns
- **Frontend-Backend Communication**: Uses Tauri's `invoke()` function to call Rust commands from JavaScript
- **Command Pattern**: Rust functions marked with `#[tauri::command]` are exposed to frontend
- **Library Structure**: Main binary calls `run()` function from library crate for better organization
- **Capabilities System**: Uses Tauri v2's capability-based security model defined in `capabilities/default.json`

### Configuration
- `tauri.conf.json` specifies frontend build directory as `../src`
- Bundle targets set to "all" for cross-platform builds
- Application identifier: `com.ych.quick-start`
- Default window: 800x600 pixels

### Dependencies
- **Frontend**: Only uses Tauri CLI for development
- **Backend**: Core Tauri runtime, tauri-plugin-opener, serde for JSON serialization
- **Build**: tauri-build for compile-time code generation

### Plugin System
Currently uses `tauri-plugin-opener` for opening URLs/files. New plugins can be added via `npx tauri add <plugin-name>`.