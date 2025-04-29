import { apiGet, apiPut } from '@/utils/api'

// Fetch single user by id
export const getUserById = (id) => {
  return apiGet(`/users/${id}`)
}

// Fetch all users
export const getAllUsers = () => {
  return apiGet('/users')
}

// Fetch users by role
export const getUsersByRole = (role) => {
  return apiGet(`/users?role=${encodeURIComponent(role)}`)
}

// Update user profile
export const updateUserProfile = (id, updateData) => {
  return apiPut(`/users/${id}`, updateData)
}
