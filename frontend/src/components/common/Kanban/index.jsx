import React, { useState } from 'react';
import { Box, Paper, Typography } from '@mui/material';
import KanbanCard from '../KanbanCard';
import mockBacklogs from '../../../mocks/mockBacklogs';

const Kanban = () => {
  const projectId = 'project-1';
  const sprintId = 201;

  const filteredTasks = mockBacklogs.filter(
    (task) => task.project_id === projectId && task.sprint_id === sprintId && task.type === 'task'
  );

  const [tasks, setTasks] = useState(filteredTasks);

  const updateTaskStatus = (id, newStatus) => {
    setTasks((prevTasks) =>
      prevTasks.map((task) =>
        task.backlog_id === id ? { ...task, status: newStatus } : task
      )
    );
  };

  const todoTasks = tasks.filter((task) => task.status === 'todo');
  const inProgressTasks = tasks.filter((task) => task.status === 'inprogress');
  const doneTasks = tasks.filter((task) => task.status === 'done');

  return (
    <Box sx={{ display: 'flex', gap: 2, height: '65vh' }}>
      <Paper sx={{ flex: 1, padding: 2, borderRadius: 2, backgroundColor: '#e0f7fa', overflowY: 'auto', height: '100%' }} elevation={3}>
        <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 2, textAlign: 'center' }}>To Do</Typography>
        {todoTasks.map((task) => (
          <KanbanCard
            key={task.backlog_id}
            title={task.title}
            status={task.status}
            titleFontSize="1.1rem"
            onChangeStatus={() => updateTaskStatus(task.backlog_id, 'inprogress')}
          />
        ))}
      </Paper>

      <Paper sx={{ flex: 1, padding: 2, borderRadius: 2, backgroundColor: '#fff3e0', overflowY: 'auto', height: '100%' }} elevation={3}>
        <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 2, textAlign: 'center' }}>In Progress</Typography>
        {inProgressTasks.map((task) => (
          <KanbanCard
            key={task.backlog_id}
            title={task.title}
            status={task.status}
            titleFontSize="1.1rem"
            onChangeStatus={(newStatus) => {
              if (newStatus === 'todo') {
                updateTaskStatus(task.backlog_id, 'todo');
              } else {
                updateTaskStatus(task.backlog_id, 'done');
              }
            }}
          />
        ))}
      </Paper>

      <Paper sx={{ flex: 1, padding: 2, borderRadius: 2, backgroundColor: '#e8f5e9', overflowY: 'auto', height: '100%' }} elevation={3}>
        <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 2, textAlign: 'center' }}>Done</Typography>
        {doneTasks.map((task) => (
          <KanbanCard
            key={task.backlog_id}
            title={task.title}
            status={task.status}
            titleFontSize="1.1rem"
            onChangeStatus={() => updateTaskStatus(task.backlog_id, 'inprogress')}
          />
        ))}
      </Paper>
    </Box>
  );
};

export default Kanban;
