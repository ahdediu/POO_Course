# 🚦Smart Traffic Flow Simulation 

## Objective
Design and implement a **traffic-flow simulator** where autonomous vehicles move through intersections under the control of traffic lights.  
The simulator models roads, vehicles, lights, sensors, and control strategies, allowing students to apply **Object-Oriented Programming** principles and **design patterns** in a modern, visual context.

---

## System Overview

### Core Entities

| Class | Responsibility |
|--------|----------------|
| `Road` | Holds lanes and connected intersections |
| `Intersection` | Manages traffic lights and vehicle queues |
| `TrafficLight` | Encapsulates light states (`RED`, `YELLOW`, `GREEN`) |
| `Vehicle` | Represents a moving car, position, speed, route |
| `Sensor` | Detects waiting cars (for adaptive lights) |
| `World` | Global clock, tick updates, simulation state |

---


## 💡 Functional Requirements
- Cars follow lanes and respect lights.
- Lights can run **fixed-timer** or **adaptive** policies.
- Adjustable simulation speed, **pause/resume**, and reset.
- Basic statistics (average waiting time, throughput).
- Visualization in **JavaFX Canvas** or `Pane`.

---

## Milestones (4 marks each — Total 20 marks)

### M1 — Model Design & Simulation Loop (4 marks)

**Deliverables**
- Classes: `Vehicle`, `Road`, `Intersection`, `TrafficLight`
- Fixed-step simulation loop (`tick()` updates car positions & light timers)
- Collision-free car movement
- UML diagram + basic unit tests, in the project report

**Marking**
| Criterion | Marks |
|------------|-------|
| Class structure & OOP principles | 2.0 |
| Working simulation tick | 1.0 |
| Documentation / tests | 1.0 |

---

### M2 — JavaFX Visualization (4 marks)

**Deliverables**
- GUI with Canvas showing cars (rectangles), roads, and lights
- Start/Stop/Reset controls
- Adjustable simulation speed (slider)
- Smooth animation (`AnimationTimer` or `Timeline`)

**Marking**
| Criterion | Marks |
|------------|-------|
| Rendering correctness | 1.5 |
| Responsive controls | 1.0 |
| Animation & usability | 1.5 |

---

### M3 — Traffic-Light Logic & Design Patterns (4 marks)

**Deliverables**
- **State pattern** for lights (`RedState`, `GreenState`, `YellowState`)
- Timer or tick-based transitions
- **Strategy pattern** for control policies (fixed cycle / adaptive)
- Vehicles obey light states; no illegal crossings

**Marking**
| Criterion | Marks |
|------------|-------|
| Correct state transitions | 1.5 |
| Strategy selection & switching | 1.5 |
| Integration with vehicles | 1.0 |

---

### M4 — Adaptive Control & Statistics (4 marks)

**Deliverables**
- Sensors detect waiting cars; lights adapt durations
- Statistics displayed:
  - average waiting time
  - number of cars served
  - queue lengths or throughput graphs
- Export results to **CSV** or **JSON**

**Marking**
| Criterion | Marks |
|------------|-------|
| Sensor system functional | 1.5 |
| Adaptive timing working | 1.5 |
| Statistics & export | 1.0 |

---

### M5 — Extensions & Technical Report (4 marks)

**Choose at least one extension:**
- Multiple intersections (grid or network)
- Emergency vehicles with priority
- Pedestrian lights & crossings
- Autonomous-car AI (anticipate lights, braking)
- Traffic optimization (e.g. genetic or reinforcement)
- 2D map editor
- Sound or visual alerts

**Technical report (3–5 pages)** explaining architecture, patterns used, test strategy, and main results.

**Marking**
| Criterion | Marks |
|------------|-------|
| Extension implemented | 2.0 |
| Correct use of patterns | 1.0 |
| Report quality | 1.0 |

---

## Suggested Team Roles (3 students)
| Role | Responsibilities |
|------|------------------|
| **Model/Simulation** | Core classes, tick loop, vehicle logic |
| **UI/Visualization** | JavaFX rendering, controls, charts |
| **Control/AI** | Adaptive logic, statistics, persistence |

---

## 🧪 Simulation Tick Example

```
for (TrafficLight light : world.getLights())
    light.update(deltaTime);

for (Vehicle v : world.getVehicles())
    v.update(deltaTime, world);

ui.refresh(world);
```

---

## 🗂️ Suggested Project Structure

```
src/
 ├─ model/
 │   ├─ Vehicle.java
 │   ├─ Road.java
 │   ├─ Intersection.java
 │   ├─ TrafficLight.java
 │   └─ state/ (LightState subclasses)
 ├─ controller/
 │   ├─ Simulation.java
 │   ├─ Strategy.java
 │   ├─ FixedCycle.java
 │   └─ AdaptiveCycle.java
 ├─ view/
 │   ├─ MainApp.java
 │   ├─ CanvasView.java
 │   └─ ControlPanel.java
 └─ util/
     ├─ CsvExporter.java
     └─ Metrics.java
```

---

## Evaluation Summary

| Milestone | Focus | Marks |
|------------|--------|-------|
| M1 | Model & simulation loop | 4 |
| M2 | JavaFX UI | 4 |
| M3 | Light logic & patterns | 4 |
| M4 | Adaptive & statistics | 4 |
| M5 | Extension + report | 4 |
| **Total** |  | **20 marks** |

---

## Notes 
- Simulations must run smoothly.  
- All deliverables must be submitted in a **Git repository**, with commits showing individual contributions.  

- **Presentation/demonstration is mandatory** for project validation.

---

**End of Specification**
