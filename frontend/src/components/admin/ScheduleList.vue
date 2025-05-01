<template>
  <div>
    <h2 class="text-xl font-bold mb-4">Existing Game Schedules</h2>

    <div v-if="schedules.length === 0" class="text-gray-600 italic">
      No schedules found.
    </div>

    <ul class="space-y-4">
      <li
        v-for="schedule in schedules"
        :key="schedule.id"
        class="p-4 border rounded shadow flex  flex-col justify-between items-center hover:bg-gray-50 transition"
      >
      <div class="flex justify-between items-center w-full">
        <div>
          <p class="font-semibold">{{ schedule.sport }}</p>
          <p class="text-sm text-gray-600">Season: {{ schedule.season }}</p>
        </div>

        <button
          @click="$emit('select', schedule)"
          class="bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-1 rounded"
        >
          Select
        </button>
        </div>

        <div class="mt-2 w-full">
          <ul
            v-if="games[schedule.id]?.length > 0"
            class="list-disc pl-5 space-y-1"
          >
            <li
              v-for="game in games[schedule.id]"
              :key="game.id"
              class="text-sm text-gray-800"
            >
              {{ game.gameDate }} — {{ game.opponent }} @ {{ game.venue }}
            </li>
          </ul>
          <p v-else class="italic text-gray-400">No games scheduled yet.</p>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
defineProps({
  schedules: {
    type: Array,
    required: true,
  },
  games: {
    type: Object,
    required: true,
  },
})

defineEmits(['select'])
</script>
