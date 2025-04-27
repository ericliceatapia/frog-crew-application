import { ref } from 'vue'

const isAuthenticated = ref(false)
const userId = ref(null)
const userRole = ref('')
const token = ref('')

const login = async (email, password) => {
  const res = {
    userId: 1,
    role: email === 'admin@tcu.edu' ? 'admin' : 'crew-member',
    token: 'fake-jwt-token',
  }

  isAuthenticated.value = true
  userId.value = res.userId
  userRole.value = res.role
  token.value = res.token
}

const logout = async () => {
  isAuthenticated.value = false
  userId.value = null
  userRole.value = ''
  token.value = ''
}

const getUserRole = () => {
  return userRole.value
}

export { isAuthenticated, userId, userRole, token, login, logout, getUserRole }
