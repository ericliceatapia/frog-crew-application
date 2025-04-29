<!-- Route: /crew-member -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" message="Loading your dashboard..."/>

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-4">My Dashboard</h1>
      <CrewProfileSummary :user="user" />
      <CrewUpcomingGames :games="games" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUserById } from '@/apis/users'
import { getAllGames } from '@/apis/games'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import CrewProfileSummary from '@/components/crew-member/CrewProfileSummary.vue'
import CrewUpcomingGames from '@/components/crew-member/CrewUpcomingGames.vue'

const user = ref(null)
const games = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const userId = localStorage.getItem('userId')

    user.value = await getUserById(userId)
    games.value = await getAllGames()
  } catch (err) {
    console.error('Error loading dashboard:', err)
  } finally {
    window.setInterval(() => {
      loading.value = false
    }, 1000)
  }
})
</script>
