<template>
  <form @submit.prevent="handleSubmit" class="bg-white p-4 rounded shadow">
    <h2 class="text-lg font-bold mb-4">
      Add Game to "{{ schedule.sport }}" Schedule ({{ schedule.season }})
    </h2>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
      <input
        v-model="gameDate"
        type="date"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
      <input
        v-model="venue"
        type="text"
        placeholder="Venue"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
      <input
        v-model="opponent"
        type="text"
        placeholder="Opponent"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>

    <div class="flex justify-end">
      <button
        type="submit"
        class="bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-2 rounded"
      >
        Add Game
      </button>
    </div>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { addGameToSchedule, getGamesBySchedule } from '@/apis/games'

const props = defineProps({
  schedule: { type: Object, required: true },
})
const emit = defineEmits(['gamesUpdated'])

const gameDate = ref('')
const venue = ref('')
const opponent = ref('')

async function handleSubmit() {
  try {
    await addGameToSchedule({
      scheduleId: Number(props.schedule.id),
      gameDate: gameDate.value,
      venue: venue.value,
      opponent: opponent.value,
      isFinalized: false,
    })
    const updateGames = await getGamesBySchedule(props.schedule.id)
    emit('gamesUpdated', {
      scheduleId: props.schedule.id,
      games: updateGames,
    })

    gameDate.value = ''
    venue.value = ''
    opponent.value = ''
  } catch (err) {
    console.error('Failed to add game:', err)
    alert('There was an error adding the game.')
  }
}
</script>
