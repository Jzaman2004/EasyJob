#!/bin/bash
# Setup script for EasyJob project

echo "Setting up EasyJob project..."

# Install backend dependencies
echo "Installing backend dependencies..."
cd backend
npm install
cd ..

# Install frontend dependencies
echo "Installing frontend dependencies..."
cd frontend
npm install
cd ..

echo "Setup complete! You can now:"
echo "  - Backend: cd backend && npm run dev"
echo "  - Frontend: cd frontend && npm start"
