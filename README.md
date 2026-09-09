# House Hacker

A mobile-friendly finance tracker for house-hacking rentals — built to replace a Google Sheet that was too inconvenient to use on the go, and to get hands-on Spring Boot reps that mirror a real enterprise Java stack.

## What it does (and why it exists)

I manage the finances for a house-hack rental on behalf of a family member. The existing system was a Google Sheet — accurate, but painful to update from a phone, which meant payments regularly went unlogged and tenants got screenshot "receipts" instead of real ones.

House Hacker is a from-scratch rebuild of that workflow as a real, usable app: log payments, view tenants and properties, and (eventually) generate real receipts — all from a phone.

## Why this project, this way

This is also a deliberate learning project. I work in Java/Spring Boot professionally, but wanted a low-stakes, real-stakes-for-me project to build actual Spring Boot reps outside of tutorial toy examples. The approach:

- Follow a structured course (Amigoscode's Spring Boot series) concept by concept
- Instead of copying the course's generic example domain, immediately adapt each concept to this app's real domain (`Tenant`, `Payment`, etc.)
- Keep the frontend in a stack I'm already comfortable with (Next.js) so all the new-territory learning stays focused on the backend

The result is a real app, learned in public, with commit history that reflects an actual learning process rather than a finished product dropped in all at once.

## Tech stack

**Backend**
- Java 25
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Flyway (migrations)
- Docker Compose (local Postgres)
- Spring AI + Claude (Anthropic) — experimental, see below

**Frontend** *(planned)*
- Next.js

## AI integration

This app uses [Spring AI](https://spring.io/projects/spring-ai) with Anthropic's Claude to experiment with AI-assisted features — currently a proof-of-concept (auto-generated tenant welcome messages). Longer-term ideas include natural-language payment logging and plain-English monthly summaries.

AI features require your own `ANTHROPIC_API_KEY` (see setup below) — the rest of the app works fully without one.

## Status

Early and actively in progress. Currently: `Tenant` entity with full CRUD, Dockerized local Postgres, and a working AI proof-of-concept. Not yet built: `Payment`/`Property` entities and relationships, DTO-based API responses, proper validation and error handling, receipt generation, and the frontend.

A few intentional TODOs are left in the code (e.g. exposing entities directly instead of DTOs) — tracked as comments, to be addressed as I work through more advanced course material.

## Running it locally

1. Clone the repo
2. `cd backend`
3. Copy `.env.example` to `.env` and fill in a database password of your choice (and an `ANTHROPIC_API_KEY` if you want to try the AI features)
4. Start Postgres: `docker compose up -d`
5. Run the app: `./mvnw spring-boot:run`
6. The API is available at `http://localhost:8080`

All data is local to your own Postgres container — nothing here connects to any real production data.

## About the name

"House hacking" is a real estate strategy of living in and renting out part of a property to offset housing costs — the strategy this app was originally built to support.

---

*This project involved collaboration with Claude (Anthropic) for planning, debugging, and pair-programming — it may appear as a contributor in commit history.*
