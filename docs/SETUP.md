# EasyJob Setup Guide

## Prerequisites
- Node.js v14 or higher
- npm or yarn
- MongoDB (local or Atlas URI)

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Jzaman2004/EasyJob.git
cd EasyJob
```

### 2. Run Setup Script
```bash
chmod +x scripts/setup.sh
./scripts/setup.sh
```

Or manually install dependencies:

### Backend Setup
```bash
cd backend
npm install
cp .env.example .env
# Edit .env with your configuration
npm run dev
```

### Frontend Setup
```bash
cd frontend
npm install
npm start
```

## Environment Variables

### Backend (.env)
- `PORT`: Server port (default: 3000)
- `NODE_ENV`: Environment (development/production)
- `DATABASE_URL`: MongoDB connection string

### Frontend
- `REACT_APP_API_URL`: Backend API URL

## Running the Application

### Development Mode
**Terminal 1 - Backend:**
```bash
cd backend
npm run dev
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```

### Production Build
```bash
# Backend
cd backend
npm start

# Frontend
cd frontend
npm run build
```
