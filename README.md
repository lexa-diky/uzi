# UZI

Minimalistic distributed load test system

## Distributed model

Controller evenly distributes work between agents

```mermaid
flowchart TD
    controller --> agent-1
    controller --> agent-2
    controller --> agent-3
```