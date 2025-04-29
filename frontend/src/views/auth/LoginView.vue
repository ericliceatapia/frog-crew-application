<!-- Route: /login -->

<template>
  <div v-if="loading" class="min-h-screen bg-[#4d1979]"></div>

  <div
    v-else
    class="flex items-center justify-center min-h-screen bg-[#4d1979]"
  >
    <div class="p-8 bg-white rounded shadow-md w-full max-w-md">
      <h2 class="text-3xl font-bold text-center mb-6">Log In</h2>

      <form @submit.prevent="handleLogin" class="space-y-6">
        <div>
          <label
            for="email"
            class="block text-sm font-medium text-gray-700 mb-1"
            >Email</label
          >
          <input
            v-model="email"
            type="email"
            id="email"
            name="email"
            placeholder="Enter your email"
            class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
            required
          />
        </div>

        <div>
          <label
            for="password"
            class="block text-sm font-medium text-gray-700 mb-1"
            >Password</label
          >
          <input
            v-model="password"
            type="password"
            id="password"
            name="password"
            placeholder="Enter your password"
            class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
            required
          />
        </div>

        <button
          type="submit"
          class="w-full bg-[#4d1979] hover:bg-[#a072c6] text-white font-bold py-2 px-4 rounded transition"
        >
          Login
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login, getUserRole } from '@/apis/auth'
import { showOverlay, hideOverlay } from '@/utils/overlay'

const email = ref('')
const password = ref('')
const loading = ref(true)

const router = useRouter()

setTimeout(() => {
  loading.value = false
}, 500)

async function handleLogin() {
  try {
    await login(email.value, password.value)
    const userRole = getUserRole()
    if (userRole === 'ADMIN') {
      router.push('/admin')
      showOverlay('Logging in...')
    } else if (userRole === 'CREW_MEMBER') {
      router.push('/crew-member')
      showOverlay('Logging in...')
    } else {
      router.push('/')
    }
  } catch (err) {
    alert(err.message || 'Login failed. Please try again.')
  } finally {
    setTimeout(() => {
      hideOverlay()
    }, 2000)
  }
}
</script>
