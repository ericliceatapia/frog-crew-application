<!-- Route: /admin/crew-list -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" />

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-4">Crew List</h1>

      <button
        @click="toggleInviteForm"
        class="bg-[#4d1979] hover:bg-[#a072c6] text-white px-4 py-2 rounded"
      >
        {{ showInvite ? 'Close Invite' : 'Invite Member' }}
      </button>

      <InviteCrewForm v-if="showInvite" @userInvited="fetchUsers" />

      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div
          v-for="user in users"
          :key="user.id"
          class="bg-white shadow p-6 rounded-lg flex flex-col justify-between hover:shadow-lg transition"
        >
          <div class="space-y-2">
            <h2 class="text-xl font-bold">
              {{ user.firstName }} {{ user.lastName }}
            </h2>
            <p><b>Email:</b> {{ user.email }}</p>
            <p><b>Phone:</b> {{ user.phoneNumber }}</p>
            <p><b>Role:</b> {{ user.role }}</p>
            <p><b>Positions:</b></p>
            {{ user.positions?.join(', ') || 'None' }}
          </div>

          <div class="flex gap-3 mt-4">
            <button
              @click="startEdit(user)"
              class="px-3 py-1 bg-blue-500 text-white rounded"
            >
              Edit
            </button>
            <button
              @click="startDelete(user)"
              class="px-3 py-1 bg-red-500 text-white rounded"
            >
              Delete
            </button>
          </div>
        </div>
      </div>

      <EditCrewForm
        v-if="selectedUser && mode === 'edit'"
        :user="selectedUser"
        @cancel="clearSelection"
        @saved="
          () => {
            clearSelection()
            fetchUsers()
          }
        "
      />
      <DeleteCrewConfirmation
        v-if="selectedUser && mode === 'delete'"
        :user="selectedUser"
        @cancel="clearSelection"
        @confirm="
          async () => {
            await deleteUserById(selectedUser.id)
            clearSelection()
            fetchUsers()
          }
        "
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getAllUsers, deleteUserById } from '@/apis/users'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import InviteCrewForm from '@/components/admin/InviteCrewForm.vue'
import EditCrewForm from '@/components/admin/EditCrewForm.vue'
import DeleteCrewConfirmation from '@/components/admin/DeleteCrewConfirmation.vue'

const users = ref([])
const loading = ref(true)

const showInvite = ref(false)
const selectedUser = ref(null)
const mode = ref(null)

function toggleInviteForm() {
  showInvite.value = !showInvite.value
}

function startEdit(user) {
  selectedUser.value = user
  mode.value = 'edit'
}

function cancelEdit() {
  selectedUser.value = null
  mode.value = null
}

function startDelete(user) {
  selectedUser.value = user
  mode.value = 'delete'
}

function clearSelection() {
  selectedUser.value = null
  mode.value = null
}

async function fetchUsers() {
  users.value = await getAllUsers()
}

onMounted(async () => {
  try {
    await fetchUsers()
  } catch (err) {
    console.error('Error loading crew list:', err)
  } finally {
    window.setInterval(() => {
      loading.value = false
    }, 500)
  }
})
</script>
