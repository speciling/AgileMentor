import React from 'react';
import { Box, Typography } from '@mui/material';
import Header from '../../components/features/Header';
import Sidebar from '../../components/features/SideBar';
import BurndownChart from '../../components/common/BurndownChart';

const BurndownPage = () => (
  <Box sx={{ display: 'flex', height: '100vh', overflow: 'hidden' }}>
    <Box sx={{ position: 'fixed', top: 0, width: '100%', zIndex: 1000 }}>
      <Header />
    </Box>

    <Box
      sx={{
        position: 'fixed',
        top: '9vh',
        left: 0,
        height: 'calc(100vh - 9vh)',
        width: '23vw',
        zIndex: 900,
      }}
    >
      <Sidebar />
    </Box>

    <Box
      component="main"
      sx={{
        position: 'fixed',
        top: '9vh',
        left: '23vw',
        width: 'calc(100vw - 23vw)',
        height: 'calc(100vh - 9vh)',
        backgroundColor: '#FAFAFA',
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
              fontSize: '3.5rem',
              whiteSpace: 'nowrap',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
            }}
          >
            Project B
          </Typography>
        </Box>
      </Box>

      <BurndownChart />
    </Box>
  </Box>
);

export default BurndownPage;
