<template>
  <div class="bg-white p-6 rounded shadow">
    <h2 class="text-xl font-bold mb-4">Upcoming Games</h2>
    <ul>
      <li v-for="game in games" :key="game.gameId" class="mb-2 border-b pb-2">
        <div>
          TCU Horned Frogs vs. {{ game.opponent }}
        </div>
        <div class="text-sm text-gray-600">{{ game.gameDate }} - {{ game.venue }}</div>
      </li>
    </ul>
    <div v-if="games.length === 0" class="text-gray-500 mt-4">
      No games scheduled yet.
    </div>
  </div>
</template>

<script setup>
defineProps({ games: Array })
</script>
