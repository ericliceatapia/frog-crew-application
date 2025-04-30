<!-- Route: /admin/game-schedule -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" />

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-4">Game Scheduling</h1>

      <CreateScheduleForm @scheduleCreated="fetchSchedules" />

      <ScheduleList
        :schedules="schedules"
        :games="gamesBySchedule"
        @select="handleSelectSchedule"
      />

      <AddGameToScheduleForm
        v-if="selectedSchedule"
        :schedule="selectedSchedule"
        @gameAdded="handleGamesUpdated"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getAllSchedules } from '@/apis/schedules'
import { getAllGames } from '@/apis/games'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import CreateScheduleForm from '@/components/admin/CreateScheduleForm.vue'
import ScheduleList from '@/components/admin/ScheduleList.vue'
import AddGameToScheduleForm from '@/components/admin/AddGameToScheduleForm.vue'

const loading = ref(true)
const schedules = ref([])
const selectedSchedule = ref(null)
const gamesBySchedule = ref({})

async function fetchSchedules() {
  schedules.value = await getAllSchedules()
}

async function fetchGamesGrouped() {
  const allGames = await getAllGames()
  const grouped = {}

  for (const game of allGames) {
    const sid = game.scheduleId
    if (!grouped[sid]) grouped[sid] = []
    grouped[sid].push(game)
  }

  gamesBySchedule.value = grouped
}

function handleSelectSchedule(schedule) {
  selectedSchedule.value = schedule
}

function handleGamesUpdated({ scheduleId, games }) {
  const index = schedules.value.findIndex((s) => s.id === scheduleId)
  if (index !== -1) {
    schedules.value[index].games = games
  }
  selectedSchedule.value = null
}

onMounted(async () => {
  try {
    await fetchSchedules()
    await fetchGamesGrouped()
  } catch (err) {
    console.error('Error loading schedules:', err)
  } finally {
    window.setInterval(() => {
      loading.value = false
    }, 500)
  }
})
</script>
