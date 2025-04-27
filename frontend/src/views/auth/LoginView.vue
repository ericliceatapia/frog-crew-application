<!-- Route: /login -->
<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="email">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          v-model="email"
          required
          placeholder="Enter your email"
        />
      </div>
      <div>
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          v-model="password"
          required
          placeholder="Enter your password"
        />
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<!-- 4d1979 -->

<script setup>
import { login, getUserRole } from '@/apis/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const email = ref('')
const password = ref('')

const router = useRouter()

async function handleLogin() {
  try {
    await login(email.value, password.value)

    const userRole = getUserRole()

    if (userRole === 'admin') {
      router.push('/admin')
    } else if (userRole === 'crew-member') {
      router.push('/crew-member')
    } else {
      router.push('/')
    }
  } catch (err) {
    console.error('Login failed:', err)
    alert('Login failed. Please try again.')
  }
}
</script>
