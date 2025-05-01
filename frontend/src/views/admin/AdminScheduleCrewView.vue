<!-- Route: /admin/crew-schedule -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" />

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-4">Assign Crew to Game</h1>

      <GameSelector :games="games" @select="handleSelectGame" />

      <div v-if="selectedGame">
        <h2 class="text-xl font-semibold mb-2">
          {{ selectedGame.opponent }} @ {{ selectedGame.venue }} —
          {{ selectedGame.gameDate }}
        </h2>

        <CrewAssignmentForm
          :game="selectedGame"
          :crewMembers="crewMembers"
          @save="handleSaveAssignments"
        />
      </div>

      <AssignedCrewList
        v-if="assignedCrew.length > 0"
        :assignments="assignedCrew"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getAllGames } from '@/apis/games'
import { getAllUsers } from '@/apis/users'
import { saveCrewList } from '@/apis/crewLists'
import { getCrewListByGame } from '@/apis/crewLists'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import GameSelector from '@/components/admin/GameSelector.vue'
import CrewAssignmentForm from '@/components/admin/CrewAssignmentForm.vue'
import AssignedCrewList from '@/components/admin/AssignedCrewList.vue'

const loading = ref(true)
const games = ref([])
const crewMembers = ref([])
const selectedGame = ref(null)
const assignedCrew = ref([])

async function handleSelectGame(game) {
  selectedGame.value = game
  assignedCrew.value = await getCrewListByGame(selectedGame.value.gameId)
  console.log('Selected Game:', selectedGame.value)
  console.log('Assigned Crew:', assignedCrew.value)
}

async function handleSaveAssignments(assignments) {
  await saveCrewList(selectedGame.value.gameId, assignments)
  alert('Crew assignments saved successfully.')
}

onMounted(async () => {
  try {
    games.value = await getAllGames()
    crewMembers.value = await getAllUsers()
  } catch (err) {
    console.error('Error loading schedules:', err)
  } finally {
    window.setInterval(() => {
      loading.value = false
    }, 500)
  }
})
</script>
