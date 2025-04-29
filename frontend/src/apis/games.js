import { apiGet, apiPut } from '@/utils/api'

// Fetch single game by id
export const getGameById = (gameId) => {
  return apiGet(`/games/${gameId}`)
}

// Fetch all games
export const getAllGames = () => {
  return apiGet('/games')
}

// Update user profile
export const updateGameDetails = (gameId, updateData) => {
  return apiPut(`/games/${gameId}`, updateData)
}
