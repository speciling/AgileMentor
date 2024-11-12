import React from 'react';
import { Box, Typography, Paper, Divider } from '@mui/material';
import Header from '../../components/features/Header';
import SideBar from '../../components/features/SideBar';
import ExternalLinkButtons from '../../components/common/ExternalLinkButtons';
import ProjectList from '../../components/common/ProjectList';
import OngoingTasksList from '../../components/common/OngoingTasksList';
import AgileNotesList from '../../components/common/AgileNotesList'

const DashboardPage = () => (
  <Box sx={{ display: 'flex', height: '100vh', overflow: 'hidden' }}>
    <Box sx={{ position: 'fixed', top: 0, width: '100%', zIndex: 1000 }}>
      <Header />
    </Box>

    <Box sx={{ position: 'fixed', top: '9vh', left: 0, height: 'calc(100vh - 9vh)', zIndex: 900 }}>
      <SideBar />
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
        overflowX: 'auto',
      }}
    >
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start', mb: 3 }}>
        <Typography
          variant="h4"
          sx={{
            fontWeight: 'bold',
            color: '#333',
            fontSize: '4vh',
          }}
        >
          임지환님의 프로젝트
        </Typography>
        
        <Box sx={{ mt: 2 }}>
          <ExternalLinkButtons />
        </Box>
      </Box>

      <Box sx={{ display: 'flex', gap: 3, mb: 3 }}>
        <Paper
          sx={{
            flex: 1.5,
            backgroundColor: '#fff',
            borderRadius: 3,
            height: '28vh',
            display: 'flex',
            flexDirection: 'column',
            overflow: 'hidden',
          }}
        >
          <Box sx={{ p: 2, pb: 0, position: 'sticky', top: 0, backgroundColor: '#fff', zIndex: 1 }}>
            <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 1 }}>
              내 프로젝트
            </Typography>
            <Divider sx={{ mb: 0 }} />
          </Box>
          <Box sx={{ p: 2, pt: 1, overflowY: 'auto', flexGrow: 1 }}>
            <ProjectList />
          </Box>
        </Paper>

        <Paper
          sx={{
            flex: 2.5,
            backgroundColor: '#fff',
            borderRadius: 3,
            height: '28vh',
            display: 'flex',
            flexDirection: 'column',
            overflow: 'hidden',
          }}
        >
          <Box sx={{ p: 2, pb: 0, position: 'sticky', top: 0, backgroundColor: '#fff', zIndex: 1 }}>
            <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 1 }}>
              진행중인 작업
            </Typography>
            <Divider sx={{ mb: 0 }} />
          </Box>
          <Box sx={{ p: 2, pt: 1, overflowY: 'auto', flexGrow: 1 }}>
            <OngoingTasksList memberId={301} />
          </Box>
        </Paper>
      </Box>

      <Paper
        sx={{
          backgroundColor: '#fff',
          borderRadius: 3,
          height: '30vh',
          display: 'flex',
          flexDirection: 'column',
          overflow: 'hidden',
        }}
      >
        <Box sx={{ p: 2, pb: 0, position: 'sticky', top: 0, backgroundColor: '#fff', zIndex: 1 }}>
          <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 1 }}>
            애자일 학습하기
          </Typography>
          <Divider sx={{ mb: 0 }} />
        </Box>
        <Box sx={{ p: 2, pt: 0, overflowY: 'auto', flexGrow: 1 }}>
          <AgileNotesList />
        </Box>
      </Paper>
    </Box>
  </Box>
);

export default DashboardPage;
