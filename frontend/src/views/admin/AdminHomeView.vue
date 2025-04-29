<!-- Route: /admin -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" message="Loading your dashboard..." />

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-4">My Dashboard</h1>

      <AdminDashboardStats
        :crewCount="crewMembers.length"
        :gameCount="games.length"
      />
      <AdminUpcomingGames :games="games" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUsersByRole } from '@/apis/users'
import { getAllGames } from '@/apis/games'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import AdminDashboardStats from '@/components/admin/AdminDashboardStats.vue'
import AdminUpcomingGames from '@/components/admin/AdminUpcomingGames.vue'

const crewMembers = ref([])
const games = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    crewMembers.value = await getUsersByRole('CREW_MEMBER')
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
