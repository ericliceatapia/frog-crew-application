<script setup>

import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const router = useRouter()

onMounted(() => {
  setTimeout(() => {
    router.push({ name: 'login' })
  }, 3000)
})

</script>

<!-- Route: /logout -->

<template>
  <div class="flex items-center justify-center min-h-screen bg-[#4d1979]">
    <div class="p-8 bg-white rounded shadow-md w-full max-w-md">
      <h1 class="text-center text-2xl font-bold">You are Logged Out</h1>
    </div>
  </div>
</template>
