import { ref, watch } from 'vue'
import { apiGet } from '@/utils/api'

const isAuthenticated = ref(!!(localStorage.getItem('userRole') || ''))
const userId = ref(null)
const userRole = ref(localStorage.getItem('userRole') || '')
const token = ref('')

const login = async (email, password) => {
  const users = await apiGet(`/users?email=${email}&password=${password}`)

  if (users.length > 0) {
    const user = users[0]
    isAuthenticated.value = true
    userId.value = user.Id
    userRole.value = user.role
    token.value = 'fake-jwt-token'

    localStorage.setItem('userId', user.id)
    localStorage.setItem('userRole', user.role)

    return {
      userId: user.id,
      role: user.role,
      token: 'fake-jwt-token',
    }
  } else {
    isAuthenticated.value = false
    userId.value = null
    userRole.value = ''
    token.value = ''
    throw new Error('Invalid email or password')
  }
}

const logout = async () => {
  isAuthenticated.value = false
  userId.value = null
  userRole.value = ''
  token.value = ''

  localStorage.removeItem('userId')
  localStorage.removeItem('userRole')
}

const getUserRole = () => {
  return userRole.value
}

watch(userRole, (newVal) => {
  if (newVal) {
    localStorage.setItem('userRole', newVal)
  } else {
    localStorage.removeItem('userRole')
  }
})

export { isAuthenticated, login, logout, getUserRole }
