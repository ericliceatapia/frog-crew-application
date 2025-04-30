import { createRouter, createWebHistory } from 'vue-router'
import { getUserRole, isAuthenticated } from '@/apis/auth'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 }
  },
  routes: [
    { path: '/', redirect: '/login' },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/auth/LoginView.vue'),
      meta: { title: 'Login', requiresAuth: false },
    },

    // Admin Routes
    {
      path: '/admin',
      component: () => import('@/layouts/AdminLayout.vue'),
      meta: { requiresAuth: true, roles: ['ADMIN'] },
      children: [
        {
          path: '',
          name: 'admin-home',
          component: () => import('@/views/admin/AdminHomeView.vue'),
          meta: { title: 'Admin Dashboard' },
        },
        {
          path: 'crew-list',
          name: 'admin-crew-list',
          component: () => import('@/views/admin/AdminCrewListView.vue'),
          meta: { title: 'View Crew Members' },
        },
        {
          path: 'crew-list/invite',
          name: 'admin-invite-crew',
          component: () => import('@/views/admin/InviteCrewView.vue'),
          meta: { title: 'Invite Crew Member' },
        },
        {
          path: 'crew-list/delete',
          name: 'admin-delete-crew',
          component: () => import('@/views/admin/DeleteCrewView.vue'),
          meta: { title: 'Delete Crew Member' },
        },
        {
          path: 'schedule/create',
          name: 'admin-create-schedule',
          component: () => import('@/views/admin/CreateGameScheduleView.vue'),
          meta: { title: 'Create Game Schedule' },
        },
        {
          path: 'schedule/add-games',
          name: 'admin-add-games',
          component: () => import('@/views/admin/AddGamesView.vue'),
          meta: { title: 'Add Games to Schedule' },
        },
        {
          path: 'schedule/assign',
          name: 'admin-assign-crew',
          component: () => import('@/views/admin/ScheduleCrewView.vue'),
          meta: { title: 'Assign Crew Member to Schedule' },
        },
      ],
    },

    // Crew Member Routes
    {
      path: '/crew-member',
      component: () => import('@/layouts/CrewLayout.vue'),
      meta: { requiresAuth: true, roles: ['CREW_MEMBER'] },
      children: [
        {
          path: '',
          name: 'crew-home',
          component: () => import('@/views/crew-member/CrewHomeView.vue'),
          meta: { title: 'Crew Member Dashboard' },
        },
        {
          path: 'view-profile',
          name: 'crew-view-profile',
          component: () => import('@/views/crew-member/ViewProfileView.vue'),
          meta: { title: 'View Profile' },
        },
        {
          path: 'schedule',
          name: 'crew-schedule',
          component: () => import('@/views/crew-member/GameScheduleView.vue'),
          meta: { title: 'View Game Schedule' },
        },
        {
          path: 'crew-list',
          name: 'crew-view-crew-list',
          component: () => import('@/views/crew-member/CrewListView.vue'),
          meta: { title: 'View Crew List' },
        },
        {
          path: 'availability',
          name: 'crew-availability',
          component: () =>
            import('@/views/crew-member/SubmitAvailabilityView.vue'),
          meta: { title: 'Submit Availability' },
        },
      ],
    },
  ],
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !isAuthenticated.value) {
    return next({ name: 'login', query: { redirect: to.fullPath } })
  }
  const userRole = getUserRole()
  if (to.meta.roles && !to.meta.roles.includes(userRole)) {
    // Optionally: redirect to role's default home
    if (userRole === 'ADMIN') return next({ name: 'admin-home' })
    if (userRole === 'CREW_MEMBER') return next({ name: 'crew-home' })
    return next({ name: 'login' })
  }
  next()
})

router.afterEach((to, from) => {
  console.log(`Successfully navigated to: ${to.fullPath}`)
})

export default router
