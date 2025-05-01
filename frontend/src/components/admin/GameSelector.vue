<template>
  <div>
    <label class="block font-semibold mb-1">Select Game</label>
    <select @change="selectGame" class="w-full border px-2 py-1 rounded">
      <option disabled selected value="">Choose a game</option>
      <option
        v-for="game in games"
        :key="game.gameId"
        :value="JSON.stringify(game)"
      >
        {{ game.gameDate }} — {{ game.opponent }} @ {{ game.venue }}
      </option>
    </select>
  </div>
</template>

<script setup>
const props = defineProps({ games: Array })
const emit = defineEmits(['select'])

function selectGame(event) {
  const game = JSON.parse(event.target.value)
  emit('select', game)
}
</script>
