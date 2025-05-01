import { apiGet, apiPost } from '@/utils/api'

export const getCrewListByGame = async (gameId) => {
  const result = await apiGet(`/crewLists?gameId=${gameId}`)
  return result.length > 0 && result[0].crewedUsers?.length > 0
    ? result[0].crewedUsers
    : []
}

export const saveCrewList = (gameId, crewedUsers) => {
  return apiPost('/crewLists', {
    gameId,
    crewedUsers,
  })
}
