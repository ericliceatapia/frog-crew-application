<template>
  <form @submit.prevent="handleInvite" class="bg-white p-4 rounded shadow">
    <h2 class="text-lg font-bold mb-4">Invite New Crew Member</h2>
    <div class="flex gap-4 mb-4">
      <input
        v-model="email"
        type="email"
        placeholder="Email"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
      <input
        v-model="password"
        type="password"
        placeholder="Password"
        class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>
    <div class="mb-4">
      <select v-model="role" class="w-full" required>
        <option value="" disabled>Select Role</option>
        <option value="CREW_MEMBER">Crew Member</option>
        <option value="ADMIN">Admin</option>
      </select>
    </div>
    <button
      type="submit"
      class="bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-2 rounded"
    >
      Invite
    </button>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { createUser } from '@/apis/users'

const emit = defineEmits(['userInvited'])

const email = ref('')
const password = ref('')
const role = ref('')

async function handleInvite() {
  await createUser({
    email: email.value,
    password: password.value,
    role: role.value,
  })
  email.value = ''
  password.value = ''
  role.value = ''
  emit('userInvited')
}
</script>
