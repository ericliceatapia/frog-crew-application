import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login' },
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/views/auth/LoginView.vue'),
    },
    {
      path: '/logout',
      name: 'logout',
      // route level code-splitting
      // this generates a separate chunk for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/views/auth/LogoutView.vue'),
    },

    // Admin Routes
    {
      path: '/admin',
      component: () => import('@/layouts/AdminLayout.vue'),
      children: [
        {
          path: '',
          name: 'admin-home',
          component: () => import('@/views/admin/AdminHomeView.vue'),
        },
        {
          path: 'crew-list',
          name: 'admin-crew-list',
          component: () => import('@/views/admin/ViewCrewListView.vue'),
        },
        {
          path: 'crew-list/invite',
          name: 'admin-invite-crew',
          component: () => import('@/views/admin/InviteCrewView.vue'),
        },
        {
          path: 'crew-list/delete',
          name: 'admin-delete-crew',
          component: () => import('@/views/admin/DeleteCrewView.vue'),
        },
        {
          path: 'schedule/create',
          name: 'admin-create-schedule',
          component: () => import('@/views/admin/CreateGameScheduleView.vue'),
        },
        {
          path: 'schedule/add-games',
          name: 'admin-add-games',
          component: () => import('@/views/admin/AddGamesView.vue'),
        },
        {
          path: 'schedule/assign',
          name: 'admin-assign-crew',
          component: () => import('@/views/admin/ScheduleCrewView.vue'),
        },
      ],
    },

    // Crew Member Routes
    {
      path: '/crew-member',
      component: () => import('@/layouts/CrewLayout.vue'),
      children: [
        {
          path: '',
          name: 'crew-home',
          component: () => import('@/views/crew-member/CrewHomeView.vue'),
        },
        {
          path: 'create-profile',
          name: 'crew-create-profile',
          component: () => import('@/views/crew-member/CreateProfileView.vue'),
        },
        {
          path: 'view-profile',
          name: 'crew-view-profile',
          component: () => import('@/views/crew-member/ViewProfileView.vue'),
        },
        {
          path: 'schedule',
          name: 'crew-schedule',
          component: () => import('@/views/crew-member/GameScheduleView.vue'),
        },
        {
          path: 'crew-list',
          name: 'crew-view-crew-list',
          component: () => import('@/views/crew-member/CrewListView.vue'),
        },
        {
          path: 'availability',
          name: 'crew-availability',
          component: () =>
            import('@/views/crew-member/SubmitAvailabilityView.vue'),
        },
      ],
    },
  ],
})

export default router
