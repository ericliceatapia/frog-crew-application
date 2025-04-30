// src/apis/schedules.js

import { apiGet, apiPost } from '@/utils/api'

// Fetch all schedules
export const getAllSchedules = () => {
  return apiGet('/gameSchedules')
}

// Create a new schedule
export const createSchedule = (newSchedule) => {
  // Expected format: { sport: 'Football', season: '2024-2025' }
  return apiPost('/gameSchedules', newSchedule)
}

// Fetch schedule by id
export const getScheduleById = (id) => {
  return apiGet(`/gameSchedules/${id}`)
}
