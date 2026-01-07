# TeamSync Interview Preparation Guide

## Section 1: Quick Project Summary

**What is TeamSync?**
TeamSync is a multi-tenant B2B project management SaaS platform that enables teams to collaborate on projects and tasks within isolated workspaces. It provides workspace management, role-based access control, and comprehensive task tracking with filtering and analytics capabilities.

**Tech Stack:**
- **Backend:** Node.js, Express.js, TypeScript, MongoDB, Mongoose
- **Frontend:** React 18, TypeScript, Vite, TailwindCSS, Shadcn UI
- **Authentication:** Passport.js (JWT, Google OAuth, Local Strategy)
- **State Management:** React Context API, TanStack Query (React Query)
- **Validation:** Zod
- **Additional:** Axios, React Router DOM, React Hook Form

**Main Features:**
- Google OAuth and email/password authentication with JWT
- Multi-workspace management with invite codes
- Project and task management (CRUD operations)
- Role-based permissions (Owner, Admin, Member)
- Task filtering by status, priority, assignee, and search
- Analytics dashboard
- Pagination and load more functionality
- Member invitation system
- Workspace switching

---

## Section 2: Architecture Overview

### Folder Structure

**Backend (`/backend`):**
- `src/config/` - Configuration files (database, passport, app settings)
- `src/controllers/` - Request handlers for routes
- `src/services/` - Business logic layer
- `src/models/` - Mongoose schemas (User, Workspace, Project, Task, Member, Role)
- `src/routes/` - API route definitions
- `src/middlewares/` - Authentication, error handling, async handlers
- `src/utils/` - Helper functions (JWT, bcrypt, role guards)
- `src/validation/` - Zod validation schemas
- `src/enums/` - TypeScript enums (roles, permissions, task status)

**Frontend (`/client`):**
- `src/components/` - Reusable UI components (organized by feature)
- `src/context/` - React Context providers (Auth, Query)
- `src/hooks/` - Custom React hooks (API calls, permissions, workspace)
- `src/page/` - Page-level components
- `src/routes/` - Route definitions
- `src/lib/` - Utility libraries
- `src/types/` - TypeScript type definitions
- `src/constant/` - Constants and enums

### Frontend-Backend Connection

1. **API Communication:** Axios-based hooks (`useAuth`, `useGetWorkspaceQuery`, etc.) make HTTP requests to Express backend
2. **Authentication Flow:** JWT tokens stored in memory/localStorage, sent via Authorization header
3. **Request Flow:** Frontend → API hooks → Axios → Express routes → Controllers → Services → Database
4. **Error Handling:** Centralized error middleware catches and formats errors, returned as JSON responses
5. **CORS:** Configured to allow frontend origin with credentials

### Database Design (Models)

**User Model:**
- Fields: name, email, password (hashed), profilePicture, currentWorkspace, isActive, lastLogin
- Methods: comparePassword, omitPassword
- Pre-save hook: Hashes password before saving

**Workspace Model:**
- Fields: name, description, owner (ref: User), inviteCode (unique)
- Represents a tenant/organization boundary

**Member Model:**
- Fields: userId (ref: User), workspaceId (ref: Workspace), role (ref: Role), joinedAt
- Junction table linking users to workspaces with roles

**Role Model:**
- Fields: name (OWNER/ADMIN/MEMBER), permissions (array)
- Default permissions assigned based on role name

**Project Model:**
- Fields: name, description, emoji, workspace (ref: Workspace), createdBy (ref: User)
- Scoped to a workspace

**Task Model:**
- Fields: taskCode (unique), title, description, project (ref: Project), workspace (ref: Workspace), status, priority, assignedTo (ref: User), createdBy (ref: User), dueDate
- Belongs to both project and workspace for multi-tenancy

**Account Model:**
- Fields: userId (ref: User), provider (GOOGLE/EMAIL), providerId
- Links authentication providers to users

---

## Section 3: Key Implementations

### Authentication (JWT + Google OAuth)

**How it works:**
1. **Google OAuth:** User clicks Google sign-in → redirects to Google → callback receives profile → creates/finds user → generates JWT → redirects to frontend with token
2. **Email/Password:** User submits credentials → Passport Local Strategy verifies → compares hashed password → generates JWT → returns token
3. **JWT Strategy:** All protected routes use Passport JWT strategy → extracts token from Authorization header → validates → attaches user to request
4. **Token Storage:** JWT stored in frontend memory/localStorage, sent in Authorization header as Bearer token

**Key Files:**
- `backend/src/config/passport.config.ts` - Strategy configurations
- `backend/src/controllers/auth.controller.ts` - Login/register handlers
- `backend/src/utils/jwt.ts` - Token signing utilities

### Multi-tenancy - Data Isolation Approach

**Implementation:**
1. **Workspace-scoped queries:** All data queries filter by `workspaceId` parameter
2. **Member validation:** Before any operation, system verifies user is a member of the workspace
3. **Route-level isolation:** API routes require `workspaceId` in URL params (e.g., `/task/workspace/:workspaceId/all`)
4. **Service-level checks:** Services validate that resources belong to the specified workspace before operations
5. **User context:** Each user has `currentWorkspace` field to track active workspace

**Example:** Task queries always include `workspace: workspaceId` in MongoDB filter, ensuring users only see tasks from workspaces they belong to.

### Role-based Access - Implementation Method

**Backend:**
1. **Role-Permission Mapping:** Predefined permissions per role (Owner: all, Admin: most, Member: limited)
2. **Member-Role Association:** Member model stores role reference, fetched with workspace data
3. **Permission Checking:** `roleGuard` utility checks if user's role has required permissions
4. **Controller-level enforcement:** Each protected endpoint calls `getMemberRoleInWorkspace` → `roleGuard` before proceeding

**Frontend:**
1. **Permission Hook:** `usePermissions` extracts permissions from workspace member data
2. **Context Integration:** AuthContext provides `hasPermission` function
3. **Component Guards:** `withPermission` HOC and `PermissionGuard` component conditionally render based on permissions

**Key Files:**
- `backend/src/utils/role-permission.ts` - Permission mappings
- `backend/src/utils/roleGuard.ts` - Permission validation
- `client/src/hooks/use-permissions.ts` - Frontend permission logic

### State Management - Context API Usage

**Architecture:**
1. **AuthContext:** Manages user data, current workspace, and permissions
    - Fetches user via `useAuth` hook
    - Fetches workspace via `useGetWorkspaceQuery`
    - Computes permissions from member role
    - Provides `hasPermission` helper

2. **QueryProvider:** TanStack Query (React Query) wrapper
    - Manages server state, caching, and refetching
    - Handles API call states (loading, error, success)
    - Reduces redundant API calls

3. **Local State:** Component-level state for UI interactions (forms, modals)

**Why Context API:** Simpler than Redux for this scope, built-in React solution, sufficient for user/auth state that doesn't change frequently.

### API Structure - Endpoint Organization

**Route Organization:**
- `/api/auth` - Authentication (login, register, Google OAuth)
- `/api/user` - User profile operations
- `/api/workspace` - Workspace CRUD, member management
- `/api/member` - Member operations (invite, remove, change role)
- `/api/project` - Project CRUD (workspace-scoped)
- `/api/task` - Task CRUD (workspace and project-scoped)

**Pattern:**
- RESTful routes with resource nesting (e.g., `/task/project/:projectId/workspace/:workspaceId/create`)
- All protected routes use `passportAuthenticateJWT` middleware
- Controllers validate input with Zod schemas
- Services contain business logic
- Consistent error handling via asyncHandler middleware

---

## Section 4: Technical Decisions

### Why MERN Stack?

- **MongoDB:** Flexible schema for evolving requirements, JSON-like documents fit JavaScript ecosystem, horizontal scaling capability
- **Express:** Lightweight, unopinionated, large ecosystem, easy to extend
- **React:** Component reusability, strong ecosystem, excellent developer experience with TypeScript
- **Node.js:** JavaScript across stack, non-blocking I/O for concurrent requests, shared code/types between frontend and backend

### Why Context API over Redux?

- **Simplicity:** Less boilerplate, easier to understand for team members
- **Scope:** Auth state is relatively simple (user, workspace, permissions) - doesn't need Redux complexity
- **Performance:** Context API sufficient for infrequently changing auth data
- **React Query:** Handles server state caching, reducing need for Redux for API data

### Why MongoDB over SQL?

- **Flexibility:** Schema can evolve without migrations (useful for MVP/rapid development)
- **Document Structure:** Nested documents fit workspace/project/task hierarchy naturally
- **JavaScript Integration:** Native JSON support aligns with Node.js/React
- **Scalability:** Horizontal scaling easier for multi-tenant SaaS
- **Rapid Development:** Faster iteration without strict schema constraints

### Security Measures Implemented

1. **Password Hashing:** bcrypt with salt rounds, passwords never stored in plain text
2. **JWT Tokens:** Stateless authentication, token expiration, secure secret key
3. **Input Validation:** Zod schemas validate all user inputs before processing
4. **Authorization:** Role-based access control enforced at controller level
5. **Multi-tenancy:** Workspace isolation prevents cross-tenant data access
6. **CORS:** Configured to allow only frontend origin
7. **Error Handling:** Generic error messages prevent information leakage
8. **Mongoose Transactions:** Ensures data consistency for multi-step operations (user creation, workspace deletion)

---

## Section 5: Interview Questions & Answers

### Project Features Questions

**Q1: What are the core features of TeamSync?**
TeamSync is a multi-tenant project management platform featuring workspace isolation, role-based access control (Owner/Admin/Member), project and task management with filtering, Google OAuth and email authentication, member invitations via invite codes, and analytics dashboards. Each workspace operates independently with its own projects, tasks, and members, ensuring complete data isolation between organizations.

**Q2: How does the workspace system work?**
Workspaces serve as tenant boundaries where each workspace has an owner, members with assigned roles, and associated projects/tasks. Users can belong to multiple workspaces and switch between them. When a user creates an account, a default workspace is automatically created. Workspaces use unique invite codes for member invitations, and all data queries are scoped to the current workspace ID to ensure isolation.

**Q3: Explain the task management features.**
Tasks belong to both a project and workspace, supporting status tracking (TODO/IN_PROGRESS/DONE), priority levels (LOW/MEDIUM/HIGH), assignment to workspace members, due dates, and descriptions. The system provides filtering by status, priority, assignee, keyword search, and due date, along with pagination for large datasets. Tasks can only be created/edited by users with appropriate permissions based on their role.

### Technical Implementation Questions

**Q4: How does authentication work in your application?**
Authentication uses Passport.js with three strategies: Google OAuth (redirects to Google, receives profile, creates/finds user), Local Strategy (email/password with bcrypt verification), and JWT Strategy (validates tokens on protected routes). Upon successful authentication, a JWT token is generated containing the user ID and returned to the frontend. The frontend stores the token and sends it in the Authorization header for subsequent requests. Protected routes use Passport JWT middleware to validate tokens and attach user data to requests.

**Q5: How is multi-tenancy implemented for data isolation?**
Multi-tenancy is achieved through workspace-scoped queries where every data operation requires a workspaceId parameter. Before any operation, the system verifies the user is a member of that workspace via the Member model. All database queries include workspace filters (e.g., `workspace: workspaceId`), and services validate that resources belong to the specified workspace. Routes include workspaceId in URLs, and the frontend derives it from the current route context, ensuring users can only access data from workspaces they belong to.

**Q6: How does role-based access control work?**
RBAC uses a three-tier system: Roles (Owner/Admin/Member) stored in the Role model with predefined permission arrays. The Member model links users to workspaces with role references. Backend controllers call `getMemberRoleInWorkspace` to fetch the user's role, then `roleGuard` validates required permissions before allowing operations. Frontend uses `usePermissions` hook to extract permissions from workspace member data, and components use `hasPermission` from AuthContext or `withPermission` HOC to conditionally render features based on user permissions.

### Challenges Questions

**Q7: What was the biggest challenge you faced?**
The biggest challenge was ensuring proper data isolation in multi-tenant architecture. Initially, some queries didn't consistently filter by workspaceId, risking cross-tenant data leaks. I solved this by implementing workspace validation middleware, making workspaceId mandatory in all routes, adding service-level checks to verify resource ownership, and creating a pattern where every data operation explicitly includes workspace context. I also used Mongoose transactions to ensure atomicity when creating users with default workspaces.

**Q8: How did you handle state management complexity?**
Managing authentication state, workspace context, permissions, and API data across components was complex. I solved this by using React Context API for global auth/workspace state, TanStack Query for server state management and caching, and custom hooks (`useAuth`, `usePermissions`) to encapsulate logic. This approach reduced prop drilling, centralized permission checks, and eliminated redundant API calls through React Query's caching, making the codebase more maintainable.

### Improvements Questions

**Q9: What improvements would you make if you had more time?**
I would add real-time collaboration using WebSockets for live task updates, implement comprehensive unit and integration tests, add email notifications for task assignments and due dates, create advanced analytics with charts and reports, implement task dependencies and subtasks, add file attachments to tasks, optimize database queries with proper indexing, implement rate limiting for API endpoints, add audit logs for sensitive operations, and create a mobile-responsive design with PWA capabilities.

**Q10: How would you scale this application?**
For scaling, I would implement database indexing on frequently queried fields (workspaceId, userId, email), add Redis caching for frequently accessed data, use CDN for static assets, implement horizontal scaling with load balancers, add database read replicas for read-heavy operations, implement pagination at database level (already done), use connection pooling for MongoDB, add monitoring and logging (e.g., Winston, Sentry), implement API rate limiting, and consider microservices architecture if specific features need independent scaling.

---

## Section 6: Code Locations

### Authentication Logic
- **Backend Auth Controller:** `backend/src/controllers/auth.controller.ts`
- **Passport Strategies:** `backend/src/config/passport.config.ts`
- **Auth Service:** `backend/src/services/auth.service.ts`
- **JWT Utilities:** `backend/src/utils/jwt.ts`
- **Auth Routes:** `backend/src/routes/auth.route.ts`
- **Frontend Auth Hook:** `client/src/hooks/api/use-auth.ts`
- **Auth Context:** `client/src/context/auth-provider.tsx`

### API Routes
- **Auth Routes:** `backend/src/routes/auth.route.ts`
- **User Routes:** `backend/src/routes/user.route.ts`
- **Workspace Routes:** `backend/src/routes/workspace.route.ts`
- **Member Routes:** `backend/src/routes/member.route.ts`
- **Project Routes:** `backend/src/routes/project.route.ts`
- **Task Routes:** `backend/src/routes/task.route.ts`
- **Main Server:** `backend/src/index.ts` (route registration)

### Database Models
- **User Model:** `backend/src/models/user.model.ts`
- **Workspace Model:** `backend/src/models/workspace.model.ts`
- **Member Model:** `backend/src/models/member.model.ts`
- **Role Model:** `backend/src/models/roles-permission.model.ts`
- **Project Model:** `backend/src/models/project.model.ts`
- **Task Model:** `backend/src/models/task.model.ts`
- **Account Model:** `backend/src/models/account.model.ts`

### State Management
- **Auth Context:** `client/src/context/auth-provider.tsx`
- **Query Provider:** `client/src/context/query-provider.tsx`
- **Permission Hook:** `client/src/hooks/use-permissions.ts`
- **Workspace Hook:** `client/src/hooks/use-workspace-id.ts`
- **API Hooks:** `client/src/hooks/api/` (various hooks for API calls)

### Main Components
- **App Entry:** `client/src/App.tsx`
- **Main Entry:** `client/src/main.tsx`
- **Layout Components:** `client/src/layout/`
- **Workspace Components:** `client/src/components/workspace/`
- **Task Components:** `client/src/components/workspace/task/`
- **Project Components:** `client/src/components/workspace/project/`
- **Member Components:** `client/src/components/workspace/member/`
- **Auth Components:** `client/src/components/auth/`
- **UI Components:** `client/src/components/ui/` (Shadcn components)
- **Permission Guard:** `client/src/components/resuable/permission-guard.tsx`
- **HOC:** `client/src/hoc/with-permission.tsx`

---

**Total Word Count: ~1,450 words**
