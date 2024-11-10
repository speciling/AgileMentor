import React from 'react';
import { Box, Typography, Paper, Divider } from '@mui/material';
import Header from '../../components/features/Header';
import SideMenu from '../../components/features/SideMenu';
import ExternalLinkButtons from '../../components/common/ExternalLinkButtons';
import ProjectList from '../../components/common/ProjectList';
import OngoingTasksList from '../../components/common/OngoingTasksList';

const DashboardPage = () => (
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
      }}
    >
      <Box
        sx={{
          display: 'flex',
          alignItems: 'center',
          mb: 5,
        }}
      >
        <Box sx={{ maxWidth: '60%', overflow: 'hidden', textOverflow: 'ellipsis' }}>
          <Typography variant="subtitle1" sx={{ color: '#333' }}>
            Project Name:
          </Typography>
          <Typography
            variant="h4"
            sx={{
              fontWeight: 'bold',
              color: '#333',
              fontSize: '4rem',
              whiteSpace: 'nowrap',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
            }}
          >
            Project B
          </Typography>
        </Box>

        <Box sx={{ marginLeft: 'auto', flexShrink: 0 }}>
          <ExternalLinkButtons />
        </Box>
      </Box>

      <Box sx={{ display: 'flex', gap: 3, mb: 3 }}>
        <Paper
          sx={{
            flex: 1,
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
              My Projects
            </Typography>
            <Divider sx={{ mb: 0 }} />
          </Box>
          <Box sx={{ p: 2, pt: 1, overflowY: 'auto', flexGrow: 1 }}>
            <ProjectList />
          </Box>
        </Paper>

        <Paper
          sx={{
            flex: 2,
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
              Ongoing Tasks
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
            Agile Notes
          </Typography>
          <Divider sx={{ mb: 0 }} />
        </Box>
        <Box sx={{ p: 2, pt: 1, overflowY: 'auto', flexGrow: 1 }}>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
          <Typography>여기에 중요한 노트나 메모를 작성할 수 있습니다.</Typography>
        </Box>
      </Paper>
    </Box>
  </Box>
);

export default DashboardPage;
