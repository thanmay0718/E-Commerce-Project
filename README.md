# 🛡️ GigShield — AI-Powered Parametric Income Insurance for India's Gig Workers

> **Guidewire DEVTrails 2026 | University Hackathon**
> Phase 1 Submission | Team: [Your Team Name]

---

## 📌 Problem Statement

India's platform-based delivery partners (Zomato, Swiggy, Zepto, Amazon, Blinkit, etc.) are the invisible backbone of our digital economy. External disruptions — extreme weather, severe pollution, civic curfews, and local strikes — can wipe out **20–30% of their monthly earnings** with zero recourse.

**GigShield** is an AI-enabled parametric insurance platform that automatically detects these disruptions, validates income loss, and triggers instant payouts — all without the worker filing a single claim.

---

## 👤 Target Personas

GigShield covers all three major delivery segments:

| Persona | Platform | Primary Disruption Risk |
|---|---|---|
| 🍔 Food Delivery Partner | Zomato / Swiggy | Heavy rain, floods, extreme heat |
| 📦 E-commerce Delivery Partner | Amazon / Flipkart | Curfews, strikes, zone closures |
| 🛒 Grocery / Q-Commerce Partner | Zepto / Blinkit / Swiggy Instamart | AQI spikes, waterlogging, flash floods |

### Persona-Based Scenarios

**Scenario 1 — Ravi (Zomato Delivery, Hyderabad)**
It's a Thursday evening. The IMD issues a heavy rain alert. Ravi's app shows drastically reduced orders. GigShield detects rainfall > 50mm/hr via Weather API, cross-validates with Ravi's GPS inactivity, and automatically initiates a payout for 4 lost working hours — credited to his UPI by morning.

**Scenario 2 — Priya (Blinkit Grocery, Delhi)**
AQI crosses 400 (Severe) in her delivery zone. The city advises against outdoor activity. GigShield detects the AQI breach, confirms Priya's policy is active for the week, and triggers an automated payout for the lost shift — no human review required.

**Scenario 3 — Arjun (Amazon E-commerce, Mumbai)**
A local bandh is declared with 6 hours notice. Arjun cannot access the pickup hub. GigShield detects a curfew/strike event via News API (geo-tagged to Mumbai), validates Arjun's delivery zone overlap, and processes compensation for lost daily wages.

---

## 🏗️ Application Workflow

```
Worker Onboarding
      │
      ▼
Risk Profiling (AI/ML) ──► Weekly Premium Calculation
      │
      ▼
Policy Activation (Weekly Subscription)
      │
      ▼
Real-Time Disruption Monitoring (Weather / AQI / News APIs)
      │
   [Trigger Detected]
      │
      ▼
Fraud Detection Engine ──► Validate GPS + Activity + History
      │
      ▼
Automated Claim Initiation (Zero Touch)
      │
      ▼
Instant Payout (UPI / Mock Gateway)
      │
      ▼
Worker Dashboard Update + Insurer Analytics
```

---

## 💰 Weekly Premium Model

GigShield is structured on a **weekly pricing basis** aligned with the earnings cycle of gig workers.

### How It Works

- Workers subscribe on a **weekly basis** (Mon–Sun coverage window)
- Premium is **dynamically calculated every week** based on hyper-local risk factors
- Workers can opt-in or opt-out each week — no long-term lock-in

### Weekly Premium Calculation Formula

```
Weekly Premium = Base Premium
               + (Risk Score × Risk Multiplier)
               - (Loyalty Discount + Zone Safety Discount)
```

| Factor | Description | Impact |
|---|---|---|
| Base Premium | Flat weekly base rate | ₹25–₹40 |
| Risk Score | AI-calculated score (0–100) based on delivery zone, historical weather, past disruption frequency | ±₹5–₹15 |
| Zone Safety Discount | Worker operates in historically low-risk zone | −₹2–₹5 |
| Loyalty Discount | Consecutive weeks subscribed | −₹1–₹3 |
| **Estimated Weekly Range** | | **₹19 – ₹55** |

### Coverage Tiers (Weekly)

| Tier | Weekly Premium | Daily Payout Cap | Max Weekly Payout |
|---|---|---|---|
| Basic | ₹25 | ₹200 | ₹600 |
| Standard | ₹39 | ₹350 | ₹1,050 |
| Premium | ₹55 | ₹500 | ₹1,500 |

> **Coverage is strictly for INCOME LOSS ONLY.** Health, life, accidents, and vehicle repairs are explicitly excluded.

---

## ⚡ Parametric Triggers

GigShield uses **4 automated parametric triggers** to detect income-disrupting events:

### Trigger 1 — Heavy Rain / Floods
- **API**: OpenWeatherMap / IMD API
- **Threshold**: Rainfall > 35mm/hr OR IMD Orange/Red Alert issued for worker's pincode zone
- **Validation**: Worker GPS shows < 2 deliveries in the disruption window
- **Payout**: Per-hour compensation for hours within the trigger window

### Trigger 2 — Extreme Heat
- **API**: OpenWeatherMap (feels_like temperature)
- **Threshold**: Feels-like temperature > 45°C between 11 AM – 4 PM
- **Validation**: Shift hours overlap with heat alert window
- **Payout**: Compensation for affected peak hours

### Trigger 3 — Severe Air Pollution (AQI)
- **API**: AQICN / OpenAQ API
- **Threshold**: AQI > 350 (Very Poor) in worker's delivery zone
- **Validation**: Worker pincode within affected zone radius
- **Payout**: Per-shift compensation if AQI sustained > 4 hours

### Trigger 4 — Curfew / Local Strike
- **API**: NewsAPI / GDELT (geo-tagged event detection)
- **Threshold**: Verified curfew/bandh event tagged to worker's city/zone
- **Validation**: Temporal overlap with worker's active shift window
- **Payout**: Full shift compensation for confirmed disruption period

---

## 🤖 AI/ML Integration Plan

### 1. Dynamic Risk Scoring (Premium Calculation)
- **Model**: Gradient Boosted Trees (XGBoost / scikit-learn)
- **Inputs**: Delivery zone, historical weather disruption frequency, seasonal patterns, platform activity data, city-level risk indices
- **Output**: Weekly risk score (0–100) → fed into premium formula
- **Phase 1 Status**: Mock risk score calculation implemented with synthetic data

### 2. Fraud Detection Engine
- **Anomaly Detection**: Isolation Forest model to flag abnormal claim patterns
- **GPS Validation**: Cross-reference worker location history with disruption geo-zone
- **Behavioral Analysis**: Compare claimed inactive hours against platform activity logs
- **Duplicate Prevention**: Hash-based deduplication on (worker_id + event_id + date)
- **Phase 1 Status**: Logic designed; model training planned for Phase 2

### 3. Predictive Risk Modelling (Phase 2+)
- **7-day forward-looking risk prediction** per delivery zone
- Allows dynamic premium adjustment before week starts
- Enables insurer dashboard to forecast likely claim volume

---

## 🧱 Tech Stack

### Backend
| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.x (Java 17) |
| Database | MySQL 8.0 |
| Authentication | JWT (JSON Web Tokens) |
| API Layer | REST APIs |
| Risk Scoring | Python microservice (scikit-learn) — Phase 2 |

### Frontend
| Layer | Technology |
|---|---|
| Web App | React 18 + Vite |
| Mobile App | React Native (Expo) |
| State Management | Redux Toolkit |
| UI Library | Tailwind CSS + shadcn/ui |

### Integrations (Planned)
| Service | Provider | Status |
|---|---|---|
| Weather API | OpenWeatherMap (Free Tier) | Planned Phase 2 |
| AQI API | AQICN / OpenAQ | Planned Phase 2 |
| News/Event API | NewsAPI | Planned Phase 2 |
| Payment Gateway | Razorpay Test Mode | Planned Phase 3 |

### Infrastructure
- **Version Control**: GitHub
- **Deployment**: Docker + Railway / Render (planned)

---

## 🗂️ Current Implementation Status (Phase 1)

| Module | Status |
|---|---|
| Database Schema & Entity Design | ✅ Complete |
| JWT Authentication (Worker + Admin) | ✅ Complete |
| Worker Registration API | ✅ Complete |
| Risk Score Calculation (Mock Data) | ✅ Complete |
| React Frontend — Auth Screens | ✅ Complete |
| Frontend ↔ Backend Integration | ✅ Complete |
| Premium Calculation Logic | 🔄 In Progress |
| Parametric Trigger Monitoring | 📋 Planned — Phase 2 |
| Fraud Detection Engine | 📋 Planned — Phase 2 |
| Automated Claim Processing | 📋 Planned — Phase 2 |
| Payout Integration | 📋 Planned — Phase 3 |
| Analytics Dashboard | 📋 Planned — Phase 3 |

---

## 📱 Platform Justification — Web + Mobile

**Why both?**

- **Mobile App (React Native)**: The primary interface for delivery workers. Workers are on the go; mobile-first UX is essential for onboarding, policy management, and receiving payout notifications. Push notifications for disruption alerts are critical.
- **Web App (React)**: Admin/insurer dashboard for policy management, analytics, fraud review, and operational oversight. A desktop-grade interface suits the insurer's workflow.

This dual-platform strategy maximises reach — workers on mobile, insurers on web — with a single shared Spring Boot backend.

---

## 🗺️ Development Roadmap

### Phase 1 (Mar 4–20) — Ideation & Foundation ✅
- [x] Entity design & database schema
- [x] JWT authentication
- [x] Worker registration
- [x] Mock risk score engine
- [x] React frontend connected to backend
- [ ] README documentation ← *this document*
- [ ] 2-minute pitch video

### Phase 2 (Mar 21–Apr 4) — Automation & Protection
- [ ] Live Weather / AQI / News API integration
- [ ] 4 parametric trigger monitors (scheduled jobs)
- [ ] Dynamic weekly premium calculator
- [ ] Zero-touch claim initiation pipeline
- [ ] Fraud detection MVP (rule-based + anomaly detection)
- [ ] Policy management UI

### Phase 3 (Apr 5–17) — Scale & Optimise
- [ ] Advanced fraud detection (GPS spoofing, synthetic claim detection)
- [ ] Razorpay test mode payout integration
- [ ] Worker dashboard (earnings protected, active coverage)
- [ ] Insurer admin dashboard (loss ratios, predictive analytics)
- [ ] Final pitch deck + 5-minute demo video

---

## 📁 Repository Structure

```
gigshield/
├── backend/                  # Spring Boot Application
│   ├── src/main/java/
│   │   └── com/gigshield/
│   │       ├── auth/         # JWT Authentication
│   │       ├── worker/       # Worker Entity & APIs
│   │       ├── policy/       # Policy Management
│   │       ├── claims/       # Claims Processing
│   │       ├── risk/         # Risk Score Engine
│   │       └── triggers/     # Parametric Trigger Monitors
│   └── src/main/resources/
│       └── application.yml
├── frontend-web/             # React Web App (Admin Dashboard)
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── store/            # Redux
│   └── package.json
├── frontend-mobile/          # React Native App (Worker App)
│   ├── src/
│   └── package.json
├── ml-service/               # Python Risk & Fraud Models (Phase 2)
└── README.md
```

---

## 👥 Team

| Name | Role |
|---|---|
| [R. Tanmay Sri Vardhan] | Full Stack / Backend (Spring Boot) |
| [M. Rakesh Reddy] | Backend (Spring Boot) |
| [J. Vinay Kumar] | Frontend (React / React Native) |
| [M. Manikanta] | AI/ML & Data |
| [K.Rohit Sri Sai] | Working on External API |

---

## 📎 Submission Links

- **GitHub Repository**: [Link to be added]
- **Demo Video (Phase 1)**: [Link to be added]

---

> *GigShield — Because every delivery partner deserves a safety net.*
