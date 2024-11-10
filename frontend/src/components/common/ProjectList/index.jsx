import React from 'react';
import { Box, Typography, IconButton, Button, Stack } from '@mui/material';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import mockProjects from '../../../mocks/mockProjects';

const ProjectList = () => (
  <Box>
    <Box display="flex" alignItems="center" mb={2}>
      <Button
        startIcon={<AddRoundedIcon />}
        variant="outlined"
        color="success"
        sx={{
          textTransform: 'none',
          fontWeight: 'bold',
          mt: 1,
          width: '100%',
        }}
      >
        Add Project
      </Button>
    </Box>

    {mockProjects.map((project) => (
      <Box
        key={project.id}
        display="flex"
        alignItems="center"
        justifyContent="space-between"
        mb={1}
      >
        <Typography variant="body1" sx={{ fontSize: '1.2rem', color: '#333' }}>
          - {project.title}
        </Typography>

        <Stack direction="row" spacing={0.1}>
          <IconButton sx={{ color: '#0eaaf9' }} aria-label="view">
            <InfoIcon fontSize="small" />
          </IconButton>
          <IconButton sx={{ color: '#43a047' }} aria-label="edit">
            <EditIcon fontSize="small" />
          </IconButton>
          <IconButton sx={{ color: '#e53935' }} aria-label="delete">
            <DeleteIcon fontSize="small" />
          </IconButton>
        </Stack>
      </Box>
    ))}
  </Box>
);

export default ProjectList;
