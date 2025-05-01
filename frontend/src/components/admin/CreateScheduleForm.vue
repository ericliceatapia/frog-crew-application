<template>
  <form @submit.prevent="handleSubmit" class="bg-white p-4 rounded shadow mb-8">
    <h2 class="text-lg font-bold mb-4">Create New Game Schedule</h2>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <input
        v-model="sport"
        type="text"
        placeholder="Sport (e.g. Football)"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
      <input
        v-model="season"
        type="text"
        placeholder="Season (e.g. 2024-2025)"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>

    <div class="mt-4 flex justify-end">
      <button
        type="submit"
        class="bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-2 rounded"
      >
        Create Schedule
      </button>
    </div>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { createSchedule } from '@/apis/schedules'

const emit = defineEmits(['scheduleCreated'])

const sport = ref('')
const season = ref('')

async function handleSubmit() {
  try {
    await createSchedule({ sport: sport.value, season: season.value })
    sport.value = ''
    season.value = ''
    emit('scheduleCreated')
  } catch (err) {
    console.error('Failed to create schedule:', err)
    alert('There was an error creating the schedule.')
  }
}
</script>
