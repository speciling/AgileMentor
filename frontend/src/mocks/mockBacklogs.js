const mockBacklogs = [
  // Project 1 - Stories and Tasks
  { backlog_id: 1, project_id: 'project-1', sprint_id: null, parent_backlog_id: null, member_id: 301, title: 'User Authentication', description: 'Implement login and signup flows', type: 'story', status: 'inprogress', priority: 85 },
  { backlog_id: 2, project_id: 'project-1', sprint_id: 201, parent_backlog_id: 1, member_id: 304, title: 'JWT Integration', description: 'Implement JWT-based authentication', type: 'task', status: 'inprogress', priority: 75 },
  { backlog_id: 3, project_id: 'project-1', sprint_id: 202, parent_backlog_id: 1, member_id: 301, title: 'OAuth Setup', description: 'Integrate OAuth for social logins', type: 'task', status: 'todo', priority: 80 },
  { backlog_id: 4, project_id: 'project-1', sprint_id: null, parent_backlog_id: null, member_id: 303, title: 'Refactor Codebase', description: 'Improve code readability', type: 'task', status: 'todo', priority: 55 },
  { backlog_id: 5, project_id: 'project-1', sprint_id: 201, parent_backlog_id: 4, member_id: 304, title: 'Code Review', description: 'Review refactored code', type: 'task', status: 'todo', priority: 68 },
  { backlog_id: 6, project_id: 'project-1', sprint_id: 202, parent_backlog_id: 1, member_id: 301, title: 'Profile Picture Upload', description: 'Allow image uploads', type: 'task', status: 'inprogress', priority: 75 },
  { backlog_id: 7, project_id: 'project-1', sprint_id: 202, parent_backlog_id: null, member_id: 303, title: 'Session Management', description: 'Enhance session handling', type: 'task', status: 'done', priority: null },
  { backlog_id: 8, project_id: 'project-1', sprint_id: null, parent_backlog_id: null, member_id: 304, title: 'Error Handling', description: 'Improve error messages for better UX', type: 'task', status: 'todo', priority: 70 },
  { backlog_id: 9, project_id: 'project-1', sprint_id: 201, parent_backlog_id: null, member_id: 302, title: 'UI Redesign', description: 'Modernize UI components', type: 'task', status: 'inprogress', priority: 65 },
  { backlog_id: 10, project_id: 'project-1', sprint_id: 201, parent_backlog_id: null, member_id: 303, title: 'Database Migration', description: 'Migrate database to new version', type: 'task', status: 'done', priority: null },
  { backlog_id: 11, project_id: 'project-1', sprint_id: null, parent_backlog_id: null, member_id: 302, title: 'User Analytics', description: 'Set up user tracking for key metrics', type: 'story', status: 'inprogress', priority: 77 },
  { backlog_id: 12, project_id: 'project-1', sprint_id: 202, parent_backlog_id: 11, member_id: 301, title: 'Heatmap Analysis', description: 'Implement heatmaps for user interaction', type: 'task', status: 'todo', priority: 60 },
  { backlog_id: 13, project_id: 'project-1', sprint_id: 202, parent_backlog_id: null, member_id: 304, title: 'Session Timeout', description: 'Implement auto session timeout', type: 'task', status: 'inprogress', priority: 64 },
  { backlog_id: 14, project_id: 'project-1', sprint_id: null, parent_backlog_id: 1, member_id: 303, title: 'Multi-factor Authentication', description: 'Add multi-factor authentication support', type: 'task', status: 'done', priority: 75 },
  { backlog_id: 15, project_id: 'project-1', sprint_id: 201, parent_backlog_id: null, member_id: 302, title: 'Data Encryption', description: 'Encrypt sensitive user data', type: 'task', status: 'inprogress', priority: 89 },
  { backlog_id: 16, project_id: 'project-1', sprint_id: null, parent_backlog_id: null, member_id: 301, title: 'User Settings', description: 'Create user settings page', type: 'task', status: 'todo', priority: 70 },
  { backlog_id: 17, project_id: 'project-1', sprint_id: 202, parent_backlog_id: null, member_id: 304, title: 'Notification System', description: 'Build notification system for user events', type: 'task', status: 'inprogress', priority: 67 },
  { backlog_id: 18, project_id: 'project-1', sprint_id: null, parent_backlog_id: 11, member_id: 303, title: 'Optimize Image Loading', description: 'Optimize image loading for faster UI', type: 'task', status: 'todo', priority: 80 },
  { backlog_id: 19, project_id: 'project-1', sprint_id: 201, parent_backlog_id: null, member_id: 301, title: 'Responsive Design', description: 'Make UI responsive to all devices', type: 'task', status: 'inprogress', priority: 85 },
  { backlog_id: 20, project_id: 'project-1', sprint_id: 202, parent_backlog_id: null, member_id: 304, title: 'Cache Optimization', description: 'Improve caching for performance', type: 'task', status: 'todo', priority: 82 },
  
  // Project 2 - Stories and Tasks
  { backlog_id: 51, project_id: 'project-2', sprint_id: null, parent_backlog_id: null, member_id: 302, title: 'Database Setup', description: 'Design and set up initial database schema', type: 'story', status: 'done', priority: null },
  { backlog_id: 52, project_id: 'project-2', sprint_id: 202, parent_backlog_id: 51, member_id: 302, title: 'Schema Design', description: 'Design database schema for user data', type: 'task', status: 'done', priority: null },
  { backlog_id: 53, project_id: 'project-2', sprint_id: null, parent_backlog_id: null, member_id: 303, title: 'User Profile Setup', description: 'Create profile page', type: 'task', status: 'todo', priority: 80 },
  { backlog_id: 54, project_id: 'project-2', sprint_id: null, parent_backlog_id: null, member_id: 304, title: 'API Documentation', description: 'Document API endpoints', type: 'docs', status: 'done', priority: null },
  { backlog_id: 55, project_id: 'project-2', sprint_id: 202, parent_backlog_id: null, member_id: 301, title: 'Backend Optimization', description: 'Optimize backend processes', type: 'task', status: 'inprogress', priority: 82 },
  { backlog_id: 56, project_id: 'project-2', sprint_id: null, parent_backlog_id: null, member_id: 303, title: 'Analytics Setup', description: 'Set up tracking for key metrics', type: 'task', status: 'todo', priority: 90 },
  { backlog_id: 57, project_id: 'project-2', sprint_id: 201, parent_backlog_id: 51, member_id: 304, title: 'Index Optimization', description: 'Optimize indexes for better performance', type: 'task', status: 'todo', priority: 67 },
  { backlog_id: 58, project_id: 'project-2', sprint_id: 202, parent_backlog_id: null, member_id: 302, title: 'Data Backup', description: 'Set up data backup protocols', type: 'task', status: 'inprogress', priority: 63 },
  { backlog_id: 59, project_id: 'project-2', sprint_id: 201, parent_backlog_id: null, member_id: 301, title: 'Logging Setup', description: 'Implement logging for all endpoints', type: 'task', status: 'todo', priority: 77 },
  { backlog_id: 60, project_id: 'project-2', sprint_id: 202, parent_backlog_id: null, member_id: 303, title: 'Security Updates', description: 'Patch security vulnerabilities', type: 'task', status: 'inprogress', priority: 68 },

  // Project 3 - Stories and Tasks
  { backlog_id: 101, project_id: 'project-3', sprint_id: null, parent_backlog_id: null, member_id: 303, title: 'Frontend Optimization', description: 'Improve frontend performance and loading speed', type: 'story', status: 'todo', priority: 92 },
  { backlog_id: 102, project_id: 'project-3', sprint_id: null, parent_backlog_id: null, member_id: 301, title: 'Add Notifications', description: 'Add notifications for actions', type: 'task', status: 'todo', priority: 72 },
  { backlog_id: 103, project_id: 'project-3', sprint_id: 203, parent_backlog_id: null, member_id: 302, title: 'Create Test Cases', description: 'Write tests for authentication flows', type: 'task', status: 'inprogress', priority: 45 },
  { backlog_id: 104, project_id: 'project-3', sprint_id: null, parent_backlog_id: null, member_id: 302, title: 'API Documentation', description: 'Document API endpoints', type: 'task', status: 'todo', priority: 88 },
  { backlog_id: 105, project_id: 'project-3', sprint_id: null, parent_backlog_id: null, member_id: 304, title: 'User Feedback Collection', description: 'Collect user feedback through surveys', type: 'task', status: 'done', priority: null },
  { backlog_id: 106, project_id: 'project-3', sprint_id: 202, parent_backlog_id: null, member_id: 304, title: 'Security Audit', description: 'Review security vulnerabilities', type: 'task', status: 'todo', priority: 85 },
  { backlog_id: 107, project_id: 'project-3', sprint_id: 203, parent_backlog_id: null, member_id: 301, title: 'Implement Caching', description: 'Add caching for faster load times', type: 'task', status: 'inprogress', priority: 60 },
  { backlog_id: 108, project_id: 'project-3', sprint_id: null, parent_backlog_id: 101, member_id: 302, title: 'Lazy Loading', description: 'Implement lazy loading for images', type: 'task', status: 'todo', priority: 57 },
  { backlog_id: 109, project_id: 'project-3', sprint_id: 202, parent_backlog_id: null, member_id: 301, title: 'Localization', description: 'Add support for multiple languages', type: 'task', status: 'done', priority: 70 },
  { backlog_id: 110, project_id: 'project-3', sprint_id: 203, parent_backlog_id: null, member_id: 304, title: 'SEO Optimization', description: 'Improve SEO for the main pages', type: 'task', status: 'inprogress', priority: 78 },
];

export default mockBacklogs;
