<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const email = ref('')
const password = ref('')
const router = useRouter()

function login() {
  console.log('Login attempted:', email.value)

  if (email.value === 'admin@tcu.edu') {
    router.push('/admin')
  } else {
    router.push('/crew-member')
  }
}
</script>

<!-- Route: /login -->

<template>
  <div class="flex items-center justify-center min-h-screen bg-[#4d1979]">
    <div class="p-8 bg-white rounded shadow-md w-full max-w-md">
      <h1 class="text-center text-3xl font-bold mb-4">Log In</h1>
      <form @submit.prevent="login">
        <label class="block text-sm font-medium mb-1">Email</label>
        <input
          v-model="email"
          type="email"
          class="w-full px-2 py-1 border rounded mb-4"
          required
        />
        <label class="block text-sm font-medium mb-1">Password</label>
        <input
          v-model="password"
          type="password"
          class="w-full px-2 py-1 border rounded mb-6"
          required
        />
        <button
          type="submit"
          class="w-full text-white py-2 rounded bg-[#4d1979] transition hover:bg-[#a072c6]"
        >
          Log In
        </button>
      </form>
    </div>
  </div>
</template>
