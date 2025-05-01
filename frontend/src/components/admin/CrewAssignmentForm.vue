<template>
  <form @submit.prevent="submitAssignments" class="space-y-6 mt-4">
    <div
      v-for="position in positions"
      :key="position"
      class="flex items-center gap-4"
    >
      <label class="w-40 font-semibold">{{ position }}</label>
      <select
        v-model="assignments[position]"
        class="flex-1 border px-2 py-1 rounded"
        required
      >
        <option disabled value="">Select crew member</option>
        <option
          v-for="user in eligibleCrew(position)"
          :key="user.id"
          :value="user.id"
        >
          {{ user.firstName }} {{ user.lastName }}
        </option>
      </select>
    </div>

    <button
      type="submit"
      class="mt-6 bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-2 rounded"
    >
      Save Assignments
    </button>
  </form>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  game: Object,
  crewMembers: Array,
})
const emit = defineEmits(['save'])

const positions = [
  'Director',
  'Technical Director',
  'Camera Operator',
  'Audio',
  'Graphics',
]

const assignments = ref(Object.fromEntries(positions.map((p) => [p, ''])))

function eligibleCrew(position) {
  return props.crewMembers.filter((user) =>
    user.positions?.includes(position.toUpperCase().replace(' ', '_')),
  )
}

function submitAssignments() {
  const crewedUsers = []

  for (const position of positions) {
    const userId = assignments.value[position]
    if (!userId) {
      alert(`Please assign a crew member to the "${position}" position.`)
      return
    }

    console.log('Looking for userId:', userId)
    console.log('Available crewMembers:', props.crewMembers)

    const user = props.crewMembers.find((u) => String(u.id) === String(userId))

    if (!user) {
      alert(`Selected user for "${position}" not found.`)
      return
    }

    crewedUsers.push({
      userId: user.id,
      fullName: `${user.firstName} ${user.lastName}`,
      position,
      reportTime: 'TBD',
    })
  }

  emit('save', crewedUsers)
}
</script>
