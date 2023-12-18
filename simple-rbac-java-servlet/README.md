# Simple Rbac Java Server

### Requirement
- Auth (Login, Logout, Register) -> everyone can do this
- Manage User (Create, Update, Delete, Get) -> only admin can do this
- When login redirect to /user if user is admin or / if user is not admin

### Routes
- Auth
    - POST /auth/login
      - No Require Auth
      - body: {username, password}
      - if ROLE is ADMIN redirect to /admin/users
      - if ROLE is USER redirect to /profile
    - POST /auth/register (No Require Auth)
        - No Require Auth
        - body: {username, password, role}
        - if ROLE is ADMIN redirect to /admin/users
        - if ROLE is USER redirect to /profile
    - POST /auth/logout (Require Auth)
        - No Require Auth
        - redirect to /
- Profile
    - GET /profile
      - Require Auth
      - if not login redirect to /auth/login
- User (Require Auth & Admin Role)
    - POST /admin/users
      - action: update, delete
      - body:
        - update: {username, password, role, id, action}
        - delete: {id, action}
    - GET /admin/users?id={$id}
        - if id is null or user 
        - edit user
    - GET /admin/users?page={$page}&limit={$limit}&search={$search}
      - query: page, limit, search
      - example: /users?page=1&limit=10&search=foo
      - default: page=1, limit=10, search=null
