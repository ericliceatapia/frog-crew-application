<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white p-6 rounded shadow-md w-96 space-y-4">
      <h2 class="text-xl font-bold text-[#4d1979]">Edit Crew Member</h2>

      <input v-model="firstName" class="w-full p-2 border rounded mb-2" placeholder="First Name" />
      <input v-model="lastName" class="w-full p-2 border rounded mb-2" placeholder="Last Name" />
      <input v-model="email" class="w-full p-2 border rounded mb-2" placeholder="Email" />
      <input v-model="phone" class="w-full p-2 border rounded mb-2" placeholder="Phone" />
      <select v-model="role" class="w-full p-2 border rounded mb-2">
        <option value="ADMIN">Admin</option>
        <option value="CREW_MEMBER">Crew Member</option>
      </select>
      <input v-model="positions" class="w-full p-2 border rounded mb-2" placeholder="Positions (comma-separated)" />

      <div class="flex justify-between mt-4">
        <button @click="$emit('cancel')" class="px-4 py-2 rounded hover:opacity-90 bg-gray-400">Cancel</button>
        <button @click="save" class="px-4 py-2 rounded hover:opacity-90 bg-[#4d1979] text-white">Save</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { updateUserProfile } from '@/apis/users'

const props = defineProps({ user: Object })
const emit = defineEmits(['cancel', 'saved'])

const firstName = ref('')
const lastName = ref('')
const email = ref('')
const phone = ref('')
const role = ref('')
const positions = ref('')

watch(
  () => props.user,
  (user) => {
    firstName.value = user.firstName
    lastName.value = user.lastName
    email.value = user.email
    phone.value = user.phoneNumber
    role.value = user.role
    positions.value = user.positions?.join(', ')
  },
  { immediate: true }
)

async function save() {
  await updateUserProfile(props.user.id, {
    firstName: firstName.value,
    lastName: lastName.value,
    email: email.value,
    phoneNumber: phone.value,
    role: role.value,
    positions: positions.value.split(',').map(p => p.trim())
  })
  emit('saved')
}
</script>
