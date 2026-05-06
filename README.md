# EasyJob

> A comprehensive job management and application tracking platform to streamline your job search journey.

## 📋 About EasyJob

EasyJob is a full-stack web application designed to help job seekers efficiently manage their job applications, track opportunities, set reminders, and stay organized throughout their job search process.

---

## 🗂️ Project Structure

The repository has been organized with a clear, scalable architecture:

```
EasyJob/
├── backend/                    # Node.js/Express API Server
│   ├── src/
│   │   └── server.js          # Main server entry point
│   ├── tests/                 # Backend test files
│   ├── package.json           # Backend dependencies
│   └── .env.example           # Backend environment template
│
├── frontend/                  # React Web Application
│   ├── src/
│   │   ├── App.js            # Main App component
│   │   ├── App.css           # App styles
│   │   ├── index.js          # React app entry point
│   │   └── index.css         # Global styles
│   ├── public/
│   │   └── index.html        # HTML template
│   └── package.json          # Frontend dependencies
│
├── config/                    # Configuration Files
│   └── .env.example          # Global environment template
│
├── docs/                      # Documentation
│   ├── ARCHITECTURE.md       # System architecture and tech stack
│   ├── SETUP.md              # Installation and setup guide
│   ├── CONTRIBUTING.md       # Contribution guidelines
│   └── README.md             # This file
│
├── scripts/                   # Utility Scripts
│   └── setup.sh              # Automated setup script
│
├── .gitignore                # Git ignore rules
└── README.md                 # Project overview (this file)
```

---

## 🚀 Quick Start

### Prerequisites
- Node.js v14 or higher
- npm or yarn
- MongoDB (local or Atlas)

### Installation

#### Option 1: Automated Setup
```bash
# Clone repository
git clone https://github.com/Jzaman2004/EasyJob.git
cd EasyJob

# Run setup script
chmod +x scripts/setup.sh
./scripts/setup.sh
```

#### Option 2: Manual Setup

**Backend:**
```bash
cd backend
npm install
cp .env.example .env
# Configure .env with your settings
npm run dev
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

---

## 💻 Development

### Backend Development
```bash
cd backend
npm run dev        # Start with hot reload
npm test           # Run tests
```

### Frontend Development
```bash
cd frontend
npm start          # Start development server
npm run build      # Create production build
npm test           # Run tests
```

---

## 🏗️ Technology Stack

### Backend
- **Runtime:** Node.js
- **Framework:** Express.js
- **Database:** MongoDB
- **Authentication:** JWT
- **Testing:** Jest

### Frontend
- **Framework:** React 18
- **Routing:** React Router v6
- **HTTP Client:** Axios
- **Styling:** CSS3

---

## 📚 Documentation

Detailed documentation is available in the `/docs` folder:

- **[ARCHITECTURE.md](docs/ARCHITECTURE.md)** - System design and tech stack details
- **[SETUP.md](docs/SETUP.md)** - Complete installation and configuration guide
- **[CONTRIBUTING.md](docs/CONTRIBUTING.md)** - Guidelines for contributing to the project

---

## 🔄 Project Organization Changes

This project has been reorganized to follow industry best practices:

### ✅ What Was Done:
1. **Created a modular directory structure** separating concerns:
   - Backend API server isolated in `/backend`
   - Frontend application isolated in `/frontend`
   - Configuration centralized in `/config`
   - Documentation organized in `/docs`
   - Utility scripts in `/scripts`

2. **Added comprehensive configuration**:
   - Environment variable templates (`.env.example`)
   - Package management with `package.json` for both frontend and backend
   - Shared `.gitignore` for clean repository

3. **Implemented essential boilerplate**:
   - Express.js server setup in backend
   - React app scaffolding in frontend
   - Proper HTML template in frontend public directory

4. **Created detailed documentation**:
   - Architecture overview (`ARCHITECTURE.md`)
   - Setup and installation guide (`SETUP.md`)
   - Contribution guidelines (`CONTRIBUTING.md`)
   - Automated setup script (`setup.sh`)

### 🎯 Benefits of New Structure:
- **Scalability:** Easy to add new modules and features
- **Maintainability:** Clear separation of concerns
- **Collaboration:** Standardized structure for team development
- **CI/CD Ready:** Organized for automated testing and deployment
- **Developer Experience:** Faster onboarding with clear documentation

---

## 🔧 Configuration

### Environment Variables
Copy environment templates and configure:

```bash
# Backend
cd backend
cp .env.example .env

# Frontend (if needed)
# Configure via .env or config/
```

See the respective `.env.example` files for available options.

---

## 📝 API Documentation

API endpoints documentation will be added as the backend expands. Current status:
- ✅ Base server setup
- ⏳ API routes (in development)
- ⏳ Database models (in development)

---

## 🧪 Testing

### Run Backend Tests
```bash
cd backend
npm test
npm run test:watch  # Watch mode
```

### Run Frontend Tests
```bash
cd frontend
npm test
```

---

## 🚀 Deployment

### Production Build

**Backend:**
```bash
cd backend
npm install --production
npm start
```

**Frontend:**
```bash
cd frontend
npm run build
# Serve the build/ directory
```

---

## 🤝 Contributing

We welcome contributions! Please see [CONTRIBUTING.md](docs/CONTRIBUTING.md) for guidelines.

### Steps to Contribute:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add YourFeature'`)
5. Push to the branch (`git push origin feature/YourFeature`)
6. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👤 Author

**Jzaman2004**

---

## 📞 Support

For issues, questions, or suggestions, please:
- Open an [issue](https://github.com/Jzaman2004/EasyJob/issues)
- Create a [discussion](https://github.com/Jzaman2004/EasyJob/discussions)

---

## 🎉 Get Started Today

```bash
git clone https://github.com/Jzaman2004/EasyJob.git
cd EasyJob
./scripts/setup.sh
```

Happy job hunting! 🚀