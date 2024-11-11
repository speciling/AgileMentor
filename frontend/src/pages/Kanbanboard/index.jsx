import React from 'react';
import { Box, Typography } from '@mui/material';
import Header from '../../components/features/Header';
import SideMenu from '../../components/features/SideMenu';
import Kanban from '../../components/common/Kanban';

const KanbanboardPage = () => (
  <Box sx={{ display: 'flex', height: '100vh', overflow: 'hidden' }}>
    <Box sx={{ position: 'fixed', top: 0, width: '100%', zIndex: 1000 }}>
      <Header />
    </Box>

    <Box sx={{ position: 'fixed', top: '76px', left: 0, height: 'calc(100vh - 76px)', zIndex: 900 }}>
      <SideMenu />
    </Box>

    <Box
      component="main"
      sx={{
        position: 'fixed',
        top: '76px',
        left: '240px',
        width: 'calc(100vw - 240px)',
        height: 'calc(100vh - 76px)',
        backgroundColor: '#f5f8ff',
        padding: 4,
        overflowY: 'auto',
        color: '#333',
      }}
    >
      <Box sx={{ display: 'flex', alignItems: 'center', mb: 3 }}>
        <Box sx={{ maxWidth: '60%', overflow: 'hidden', textOverflow: 'ellipsis' }}>
          <Typography variant="subtitle1">Project Name:</Typography>
          <Typography
            variant="h4"
            sx={{
              fontWeight: 'bold',
              fontSize: '4rem',
              whiteSpace: 'nowrap',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
            }}
          >
            Project B
          </Typography>
        </Box>
      </Box>

      <Kanban />

    </Box>
  </Box>
);

export default KanbanboardPage;
