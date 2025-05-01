import { apiGet, apiPost, apiPut } from '@/utils/api'

// Fetch single game by id
export const getGameById = (gameId) => {
  return apiGet(`/games/${gameId}`)
}

// Fetch all games
export const getAllGames = () => {
  return apiGet('/games')
}

// Add a new game to the schedule
export const addGameToSchedule = (game) => {
  return apiPost('/games', game)
}

// Fetch all games for a specific schedule
export const getGamesBySchedule = (scheduleId) => {
  return apiGet(`/games?scheduleId=${scheduleId}`)
}

// Update games
export const updateGameDetails = (gameId, updateData) => {
  return apiPut(`/games/${gameId}`, updateData)
}
