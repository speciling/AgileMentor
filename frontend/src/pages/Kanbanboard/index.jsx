import React from 'react';
import { Box, Typography } from '@mui/material';
import SideBar from '../../components/features/SideBar';
import Kanban from '../../components/common/Kanban';

const KanbanboardPage = () => (
  <Box sx={{ display: 'flex', height: '100vh-9vh' }}>
    <SideBar />

    <Box
      component="main"
      sx={{
        left: '18vw',
        width: 'calc(100vw - 18vw)',
        height: 'calc(100vh - 9vh)',
        backgroundColor: '#FAFAFA',
        padding: '0 20px',
        overflowY: 'auto',
        overflowX: 'auto',
        color: '#333',
      }}
    >
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          mb: 3,
          marginLeft: '5vw',
          marginTop: '5vh',
        }}
      >
        <Typography
          variant="h6"
          sx={{
            fontWeight: 'bold',
            fontSize: '3.3vh',
            whiteSpace: 'nowrap',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
          }}
        >
          프로젝트 A
        </Typography>
        <Typography
          variant="body2"
          sx={{
            fontSize: '2vh',
            whiteSpace: 'nowrap',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
            marginTop: '0.5vh',
            color: '#3A3A3A',
          }}
        >
          칸반 보드
        </Typography>
      </Box>

      <Kanban />
    </Box>
  </Box>
);

export default KanbanboardPage;
