import { apiGet, apiPut, apiPost, apiDelete } from '@/utils/api'

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

// Delete user by id
export const deleteUserById = (id) => {
  return apiDelete(`/users/${id}`)
}

// Create new user
export const createUser = (userData) => {
  return apiPost('/users', userData)
}